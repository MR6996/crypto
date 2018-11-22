package cryptography;

import java.math.BigInteger;

//TODO Write documentation

public class Point implements Groupable<Point> {

	BigInteger x;
	BigInteger y;

	public Point(BigInteger x, BigInteger y) {
		this.x = x;
		this.y = y;
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

	public Point getElement() {
		return this;
	}
	
	public Point add(Point other, BigInteger module) {
		// TODO implement sum
		return null;
	}

	public Point multiply(Point other, BigInteger module) {
		// TODO implement multiply
		return null;
	}

	public Point getInverse(BigInteger module) {
		// TODO implement inverse
		return null;
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
		return "[" + x + "; " + y + "]";
	}
	
	
	
}
