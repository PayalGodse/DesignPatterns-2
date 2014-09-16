package utility;

public class UniqIdentity {

	private int count;
	private static UniqIdentity instance = null;
	// some random number to generate ID

	private UniqIdentity() {
		count = 4520;
	}

	public static UniqIdentity instance() {
		if(instance == null)
			instance = new UniqIdentity();
		return instance;
	}

	public int increase() {
		return ++count;
	}

}
