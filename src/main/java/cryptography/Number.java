package cryptography;

import java.math.BigDecimal;
import java.math.MathContext;

//TODO Write documentation

public class Number implements Groupable<Number> {

	BigDecimal value;

	public Number(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Number getElement() {
		return this;
	}
	
	public Number add(Number other, BigDecimal module) {
		return new Number(this.value.multiply(other.value).remainder(module));
	}

	public Number multiply(Number other, BigDecimal module) {
		//TODO implement multiply
		return null;
	}

	public Number getInverse(BigDecimal module) {
		return new Number(this.value.negate(MathContext.UNLIMITED));
	}

}
