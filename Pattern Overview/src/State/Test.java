package State;

public class Test {

	public static void main(String[] args) {

		Mobile mobile = new Mobile();
		mobile.alert();
		
		mobile.setState(new Silent());
		mobile.alert();
	}
}
