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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Number other = (Number) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
	

}
