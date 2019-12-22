package Adapter;

public class Test {

	public static void main(String[] args) {
		BirdAdapter birdAdapter = new BirdAdapter(new Sparrow());
		
		birdAdapter.squeak();
	}
}
