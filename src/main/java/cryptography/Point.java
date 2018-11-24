package cryptography;

import java.math.BigInteger;

//TODO Write documentation

public class Point implements Groupable<Point> {

	public static final Point OMEGA = new Point(BigInteger.ZERO, BigInteger.ONE, BigInteger.ZERO);

	BigInteger x;
	BigInteger y;
	BigInteger z;

	public Point(BigInteger x, BigInteger y) {
		this(x, y, BigInteger.ONE);
	}

	public Point(BigInteger x, BigInteger y, BigInteger z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public BigInteger getX() {
		return x;
	}

	public void setX(BigInteger x) {
		this.x = x;
	}

	public BigInteger getY() {
		return y;
	}

	public void setY(BigInteger y) {
		this.y = y;
	}

	public BigInteger getZ() {
		return z;
	}

	public Point getElement() {
		return this;
	}

	public Point add(Point other, BigInteger module) {
		if (this.z.equals(BigInteger.ZERO))
			return other;

		if (other.z.equals(BigInteger.ZERO))
			return this;

		//@formatter:off
		if (this.x.equals(other.x)) {
			if (this.y.add(other.y).equals(BigInteger.ZERO))
				return OMEGA;
			else {
				//inv = inverse((2 * a.y)%n, n)
				//x2p = ( ((3*a.x**2)*inv)**2 - 2*a.x ) %n
				//return Point( x2p , (-a.y -(3*a.x**2)*inv*(x2p - a.x)) %n )
				BigInteger x2pPartial =  this.x
											.pow(2)
											.multiply(new BigInteger("3"))
											.multiply(
												this.y
													.add(this.y)
													.modInverse(module)
											)
											.mod(module);
				BigInteger x2p = x2pPartial
									.pow(2)
									.subtract(this.x)
									.subtract(this.x)
									.mod(module);
				return new Point(
					x2p,
					this.y.negate().subtract(x2pPartial.multiply(x2p.subtract(this.x))).mod(module)
				);
			}
		} else {
			// m = ((a.y - b.y)*inverse((a.x - b.x)%n, n)) %n
			BigInteger m = this.y.subtract(other.y).multiply(this.x.subtract(other.x).modInverse(module)).mod(module);
			// (m**2 - a.x - b.x) %n, (-a.y-m*(m**2 - 2*a.x - b.x)) %n
			return new Point(
					m.multiply(m)
						.subtract(this.x)
						.subtract(other.x)
						.mod(module), 
					this.y
						.negate()
						.subtract(
							m.multiply(m)
								.subtract(this.x)
								.subtract(this.x)
								.subtract(other.x)
								.multiply(m)
						)
						.mod(module)
			);
			
		}
		// @formatter:on

		// TODO implement sum ☺
	}

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

		// TODO implement multiply ☺
		return result;
	}

	public Point getInverse(BigInteger module) {
		// TODO implement inverse ☺
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
