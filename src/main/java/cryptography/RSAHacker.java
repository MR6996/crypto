package cryptography;

import java.math.BigInteger;

//TODO write documentation.

public class RSAHacker {


	private static final int CERTAINTY = 6;
	
	private BigInteger n;
	private BigInteger e;
	private BigInteger d;
	private BigInteger p;
	private BigInteger q;
	private BigInteger lambda;

	
	public RSAHacker(BigInteger n, BigInteger e) throws ArithmeticException {
		this.n = n;
		this.e = e;

		this.p = Utils.rhoFactorization(n);
		this.q = n.divide(p);
		
		if(!p.isProbablePrime(CERTAINTY) || !q.isProbablePrime(CERTAINTY)) 
			throw new ArithmeticException();
		
		BigInteger p_1 = p.subtract(BigInteger.ONE);
		BigInteger q_1 = q.subtract(BigInteger.ONE);
		this.lambda = p_1.multiply(q_1).divide(p_1.gcd(q_1));
		
		BigInteger[] bezoutResult = Utils.bezout(lambda, e);
		if(!bezoutResult[2].equals(BigInteger.ONE))
			throw new ArithmeticException();
		this.d = bezoutResult[0];
	}

	public BigInteger getN() {
		return n;
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getLambda() {
		return lambda;
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
