package cryptography;

import java.math.BigInteger;
import java.math.MathContext;

//TODO Write documentation

public class Number implements Groupable<Number> {

	BigInteger value;

	public Number(BigInteger value) {
		this.value = value;
	}

	public BigInteger getValue() {
		return value;
	}

	public void setValue(BigInteger value) {
		this.value = value;
	}

	public Number getElement() {
		return this;
	}
	
	public Number add(Number other, BigInteger module) {
		return new Number(this.value.multiply(other.value).remainder(module));
	}

	public Number multiply(Number other, BigInteger module) {
		//TODO implement multiply
		return null;
	}

	public Number getInverse(BigInteger module) {
		return new Number(this.value.negate());
	}

}
