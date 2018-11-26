package cryptography;

import java.math.BigInteger;

public class RSAHacker {

	private BigInteger n;
	private BigInteger e;
	private BigInteger d;

	public RSAHacker(BigInteger n, BigInteger e) {
		this.n = n;
		this.e = e;

		BigInteger p_1 = Utils.rhoFactorization(n);
		BigInteger q_1 = n.divide(p_1).subtract(BigInteger.ONE);
		p_1 = p_1.subtract(BigInteger.ONE);
		BigInteger lamda = p_1.multiply(q_1).divide(p_1.gcd(q_1));

		this.d = Utils.bezout(lamda, e)[0];
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getPublicKey() {
		return e;
	}

	public BigInteger getPrivateKey() {
		return d;
	}

	public BigInteger decrypt(BigInteger c) {
		return c.modPow(d, n);
	}

}
