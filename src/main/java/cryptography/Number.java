package cryptography;

import java.math.BigInteger;


/**
 * Describe an integer as element of a mathematical group. This allow to defines
 * a sum operation between point. In this case integers are in Galois field,
 * specifying a prime number (in the methods this is the module parameter).
 * 
 * @author Mario Randazzo
 *
 */
public class Number implements Groupable<Number> {

	/**
	 * The integer value.
	 */
	private BigInteger value;

	/**
	 * Build a {@link Number} from a {@link BigInteger}.
	 * 
	 * @param value a {@link BigInteger}
	 */
	public Number(BigInteger value) {
		this.value = value;
	}

	/**
	 * Translate a integer from a string and build a {@link Number}.
	 * 
	 * @param value a String contains the representation of an integer.
	 */
	public Number(String value) {
		this.value = new BigInteger(value);
	}

	/**
	 * Get the integer value.
	 * 
	 * @return a {@link BigInteger}.
	 */
	public BigInteger getValue() {
		return value;
	}

	/**
	 * Set the integer value.
	 * 
	 * @param value {@link BigInteger}
	 */
	public void setValue(BigInteger value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#getElement()
	 */
	public Number getElement() {
		return this;
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#add(java.lang.Object, java.math.BigInteger)
	 */
	public Number add(Number other, BigInteger module) {
		return new Number(this.value.multiply(other.value).remainder(module));
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#multiply(java.math.BigInteger, java.math.BigInteger)
	 */
	public Number multiply(BigInteger n, BigInteger module) {
		return new Number(this.value.modPow(n, module));
	}

	/* (non-Javadoc)
	 * @see cryptography.Groupable#getInverse(java.math.BigInteger)
	 */
	public Number getInverse(BigInteger module) {
		return new Number(this.value.modInverse(module));
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
