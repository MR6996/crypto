package cryptography;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

//TODO Write documentation

public class Utils {

	private static final int CERTAINTY = 50;

	public static <T extends Groupable<T>> BigInteger discreteLog(T n, T g, BigInteger m, long attempts)
			throws ArithmeticException {
		if (n.equals(g))
			return new BigInteger("1");

		T temp = g.add(g, m);
		long i = 2;
		while (!n.equals(temp)) {
			if (i > attempts)
				throw new ArithmeticException();
			temp = temp.add(g, m);
			i++;
		}

		return new BigInteger(Long.toString(i));
	}

	public static <T extends Groupable<T>> List<T> discreteLogSteps(T n, T g, BigInteger m, long attempts)
			throws ArithmeticException {
		ArrayList<T> steps = new ArrayList<T>();

		if (!n.equals(g)) {
			T temp = g.add(g, m);
			steps.add(temp);
			long i = 2;
			while (i < attempts && !n.equals(temp)) {
				if (i > attempts)
					throw new ArithmeticException();
				temp = temp.add(g, m);
				steps.add(temp);
				i++;
			}
		}

		return steps;
	}

	public static <T extends Groupable<T>> BigInteger discreteLog(T n, T g, BigInteger m) throws ArithmeticException {
		return discreteLog(n, g, m, 100000);
	}

	public static <T extends Groupable<T>> List<T> discreteLogSteps(T n, T g, BigInteger m) throws ArithmeticException {
		return discreteLogSteps(n, g, m, 100000);
	}

	public static BigInteger rhoFactorization(BigInteger n) {
		BigInteger xi = f(new BigInteger("2"), n);
		BigInteger x2i = f(xi, n);
		BigInteger gcd;

		do {
			if (!(gcd = x2i.subtract(xi).gcd(n)).equals(BigInteger.ONE))
				if (gcd.isProbablePrime(CERTAINTY))
					return gcd;
				else
					return rhoFactorization(gcd);

			xi = f(xi, n);
			x2i = f(f(x2i, n), n);

		} while (true);

	}

	private static BigInteger f(BigInteger x, BigInteger module) {
		return x.multiply(x).add(BigInteger.ONE).mod(module);
	}

	public static BigInteger[] bezout(BigInteger a, BigInteger b) {
		BigInteger tmp;
		if (a.compareTo(b) > 0) {
			tmp = a;
			a = b;
			b = tmp;
		}

		BigInteger s0 = BigInteger.ONE, s1 = BigInteger.ZERO;
		BigInteger t0 = BigInteger.ZERO, t1 = BigInteger.ONE;

		BigInteger[] r = b.divideAndRemainder(a);
		while (r[1].compareTo(BigInteger.ZERO) > 0) {
			tmp = s1;
			s1 = s0.subtract(r[0].multiply(s1));
			s0 = tmp;
			tmp = t1;
			t1 = t0.subtract(r[0].multiply(t1));
			t0 = tmp;

			b = a;
			a = r[1];
			r = b.divideAndRemainder(a);
		}

		return new BigInteger[] { t1, s1, a };
	}

	public static List<BezoutStep> bezoutSteps(BigInteger a, BigInteger b) {
		ArrayList<BezoutStep> steps = new ArrayList<BezoutStep>();
		BigInteger tmp;

		if (a.compareTo(b) > 0) {
			tmp = a;
			a = b;
			b = tmp;
		}

		BigInteger s0 = BigInteger.ONE, t0 = BigInteger.ZERO;
		steps.add(new BezoutStep(b.toString(), s0.toString(), t0.toString()));
		BigInteger s1 = BigInteger.ZERO, t1 = BigInteger.ONE;
		steps.add(new BezoutStep(a.toString(), s1.toString(), t1.toString()));

		BigInteger[] r = b.divideAndRemainder(a);
		while (r[1].compareTo(BigInteger.ZERO) > 0) {
			tmp = s1;
			s1 = s0.subtract(r[0].multiply(s1));
			s0 = tmp;
			tmp = t1;
			t1 = t0.subtract(r[0].multiply(t1));
			t0 = tmp;

			steps.add(new BezoutStep(r[1].toString(), s1.toString(), t1.toString(), r[0].toString()));

			b = a;
			a = r[1];
			r = b.divideAndRemainder(a);
		}

		return steps;
	}

}
