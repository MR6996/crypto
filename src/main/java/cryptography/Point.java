package cryptography;

import java.math.BigInteger;

/**
 * Describe a point in projective coordinates of an elliptic curve as an element
 * of a mathematical group. This allow to defines a sum operation between point.
 * In this case all operation are defined over Galois field, specifying a prime
 * number (in the methods this is the module parameter).
 * 
 * @author Mario Randazzo
 *
 */
public class Point implements Groupable<Point> {

	/**
	 * Define the zero element. This is the point at infinity, that is [0;1;0] in
	 * projective coordinates.
	 */
	public static final Point OMEGA = new Point(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);

	/**
	 * The x coordinate of the point.
	 */
	private BigInteger x;

	/**
	 * The y coordinate of the point.
	 */
	private BigInteger y;

	/**
	 * The z coordinate of the point.
	 */
	private BigInteger z;

	/**
	 * Build a point specifying only the x and y coordinates as {@link BigInteger}.
	 * By default the z coordinates is one.
	 * 
	 * @param x the x coordinates of the point.
	 * @param y the y coordinates of the point.
	 */
	public Point(BigInteger x, BigInteger y) {
		this(x, y, BigInteger.ONE);
	}

	/**
	 * Build a point specifying x,y and z coordinates as {@link BigInteger}.
	 * 
	 * @param x the x coordinates of the point.
	 * @param y the y coordinates of the point.
	 * @param z the z coordinates of the point.
	 */
	public Point(BigInteger x, BigInteger y, BigInteger z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Build a point specifying only the x and y coordinates as {@link BigInteger}
	 * translated from a {@link String}. By default the z coordinates is one.
	 * 
	 * @param x the x coordinates of the point.
	 * @param y the y coordinates of the point.
	 */
	public Point(String x, String y) {
		this(new BigInteger(x), new BigInteger(y));
	}

	/**
	 * Build a point specifying x,y and z coordinates as {@link BigInteger}
	 * translated from a {@link String}.
	 * 
	 * @param x the x coordinates of the point.
	 * @param y the y coordinates of the point.
	 * @param z the z coordinates of the point.
	 */
	public Point(String x, String y, String z) {
		this.x = new BigInteger(x);
		this.y = new BigInteger(y);
		this.z = new BigInteger(z);
	}

	/**
	 * Get the x coordinates.
	 * 
	 * @return x coordinates.
	 */
	public BigInteger getX() {
		return x;
	}

	/**
	 * Set the x coordinates.
	 * 
	 * @param x a {@link BigInteger}}
	 */
	public void setX(BigInteger x) {
		this.x = x;
	}

	/**
	 * Get the y coordinates.
	 * 
	 * @return y coordinates.
	 */
	public BigInteger getY() {
		return y;
	}

	/**
	 * Set the y coordinates.
	 * 
	 * @param y a {@link BigInteger}}
	 */
	public void setY(BigInteger y) {
		this.y = y;
	}

	/**
	 * Get the z coordinates.
	 * 
	 * @return z coordinates.
	 */
	public BigInteger getZ() {
		return z;
	}

	/**
	 * Set the z coordinates.
	 * 
	 * @param z a {@link BigInteger}}
	 */
	public void setZ(BigInteger z) {
		this.z = z;
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#getElement()
	 */
	public Point getElement() {
		return this;
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#add(java.lang.Object, java.math.BigInteger)
	 */
	public Point add(Point other, BigInteger module) {
		if (this.z.equals(BigInteger.ZERO))
			return other;

		if (other.z.equals(BigInteger.ZERO))
			return this;

		// @formatter:off
		if (this.x.equals(other.x)) {
			if (this.y.add(other.y).equals(BigInteger.ZERO))
				return OMEGA;
			else {
				// inv = inverse((2 * a.y)%n, n)
				// x2p = ( ((3*a.x**2)*inv)**2 - 2*a.x ) %n
				// return Point( x2p , (-a.y -(3*a.x**2)*inv*(x2p - a.x)) %n )
				BigInteger x2pPartial = this.x.pow(2).multiply(new BigInteger("3"))
						.multiply(this.y.add(this.y).modInverse(module)).mod(module);
				BigInteger x2p = x2pPartial.pow(2).subtract(this.x).subtract(this.x).mod(module);
				return new Point(x2p, this.y.negate().subtract(x2pPartial.multiply(x2p.subtract(this.x))).mod(module));
			}
		} else {
			// m = ((a.y - b.y)*inverse((a.x - b.x)%n, n)) %n
			BigInteger m = this.y.subtract(other.y).multiply(this.x.subtract(other.x).modInverse(module)).mod(module);
			// (m**2 - a.x - b.x) %n, (-a.y-m*(m**2 - 2*a.x - b.x)) %n
			return new Point(m.multiply(m).subtract(this.x).subtract(other.x).mod(module),
					this.y.negate()
							.subtract(m.multiply(m).subtract(this.x).subtract(this.x).subtract(other.x).multiply(m))
							.mod(module));

		}
		// @formatter:on
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#multiply(java.math.BigInteger, java.math.BigInteger)
	 */
	public Point multiply(BigInteger n, BigInteger module) {
		Point result = OMEGA;
		Point partial = this;
		int j = 0;

		if (n.testBit(0)) {
			result = result.add(partial, module);
			j++;
		}

		for (int i = 1; i < n.bitLength() && j < n.bitCount(); i++) {
			partial = partial.add(partial, module);
			if (n.testBit(i)) {
				result = result.add(partial, module);
				j++;
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#getInverse(java.math.BigInteger)
	 */
	public Point getInverse(BigInteger module) {
		return new Point(x, y.negate());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[" + x + "; " + y + "; " + z + "]";
	}

}
