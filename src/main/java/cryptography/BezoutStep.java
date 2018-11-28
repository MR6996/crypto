package cryptography;

public class BezoutStep {

	private String r;
	private String s;
	private String t;
	private String q;

	public BezoutStep(String r, String s, String t) {
		this.r = r;
		this.s = s;
		this.t = t;
		this.q = null;
	}

	public BezoutStep(String r, String s, String t, String q) {
		this(r, s, t);
		this.q = q;
	}

	public String getR() {
		return r;
	}

	public String getS() {
		return s;
	}

	public String getT() {
		return t;
	}

	public String getQ() {
		return q;
	}

	public boolean isInitialStep() {
		return this.q == null;
	}

	@Override
	public String toString() {
		if (q == null)
			return "BezoutStep [r=" + r + ", s=" + s + ", t=" + t + "]";
		else
			return "BezoutStep [r=" + r + ", s=" + s + ", t=" + t + ", q=" + q + "]";
	}

}
