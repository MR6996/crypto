package cryptography;

import java.math.BigInteger;

public class Utils {

	public static <T extends Groupable<T>> BigInteger discreteLog(T n, T g, BigInteger m, long attempt) {
		//TODO write unit test for discreteLog
		if (n.equals(g))
			return new BigInteger("1");

		T temp = g.add(g, m);
		long i = 2;
		while (i < attempt && !n.equals(temp)) {
			temp = temp.add(g, m);
			i++;
		}

		return new BigInteger(Long.toString(i));
	}

	public static <T extends Groupable<T>> BigInteger discreteLog(T n, T g, BigInteger m) {
		return discreteLog(n, g, m, 100000);
	}

}
