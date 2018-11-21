package cryptography;

import java.math.BigInteger;
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
	
}
