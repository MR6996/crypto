package cryptography;

import java.math.BigInteger;

public class ElGamalHacker<T extends Groupable<T>> {

	private T gamma;
	private BigInteger p;

	private BigInteger a;
	private T alfa;

	private BigInteger b;
	private T beta;

	private T secretKey;

	public ElGamalHacker(T gamma, T alfa, T beta, BigInteger p) throws ArithmeticException {
		this.gamma = gamma;
		this.alfa = alfa;
		this.beta = beta;
		this.p = p;

		this.a = Utils.discreteLog(alfa, gamma, p);
		this.b = Utils.discreteLog(beta, gamma, p);

		this.secretKey = beta.multiply(a, p);

		if (!secretKey.equals(alfa.multiply(b, p)))
			throw new ArithmeticException();
	}

	public T getGamma() {
		return gamma;
	}

	public BigInteger getA() {
		return a;
	}

	public T getAlfa() {
		return alfa;
	}

	public BigInteger getB() {
		return b;
	}

	public T getBeta() {
		return beta;
	}

	public BigInteger getP() {
		return p;
	}

	public T getSecretKey() {
		return secretKey;
	}

	public T decrypt(T c) {
		return c.add(secretKey.getInverse(p), p);
	}

}
