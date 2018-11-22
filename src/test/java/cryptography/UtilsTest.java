package cryptography;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class UtilsTest {

	@Test
	public void discretLogSimpleTest() {
		BigInteger p = new BigInteger("9");
		BigInteger a = new BigInteger("5");
		Number gamma = new Number(new BigInteger("2"));
		Number alfa = gamma.multiply(a, p);
		assertEquals(a, Utils.discreteLog(alfa, gamma, p));
	}

}
