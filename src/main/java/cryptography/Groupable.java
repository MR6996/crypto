package cryptography;

import java.math.BigInteger;

//TODO Complete documentation

/**
 * Describe the operation of a mathematical Group of elements T.
 * 
 * @author Mario Randazzo
 *
 * @param <T> a element of the group.
 */
public interface Groupable<T> {

	/**
	 * Get the element T
	 * 
	 * @return the element T
	 */
	public T getElement();

	/**
	 * Return the sum between element T and another element.
	 * 
	 * @param other  an element T
	 * @param module
	 * @return the sum between element T and another element.
	 */
	public T add(T other, BigInteger module);

	/**
	 * Return the multiplication between element T and another element, that is the
	 * iteration of {@link add} operation.
	 * 
	 * @param other  an element T
	 * @param module
	 * @return the multiplication between element T and another element.
	 */
	public T multiply(T other, BigInteger module);

	/**
	 * Return the inverse of element T.
	 * 
	 * @param module
	 * @return the inverse of element T.
	 */
	public T getInverse(BigInteger module);

}
