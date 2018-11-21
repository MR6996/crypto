package cryptography;

import java.math.BigDecimal;
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
	
	public Point add(Point other, BigDecimal module) {
		// TODO implement sum
		return null;
	}

	public Point multiply(Point other, BigDecimal module) {
		// TODO implement multiply
		return null;
	}

	public Point getInverse(BigDecimal module) {
		// TODO implement inverse
		return null;
	}
	
}
