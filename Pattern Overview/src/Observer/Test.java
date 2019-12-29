package Observer;

public class Test {

	public static void main(String[] args) {
		
		Channel bobLukas = new Channel();
		
		Subscriber s1 = new Subscriber("Martin");
		Subscriber s2 = new Subscriber("Sarah");
		Subscriber s3 = new Subscriber("Lisa");
		Subscriber s4 = new Subscriber("Eric");
		
		bobLukas.subscribe(s1);
		bobLukas.subscribe(s2);
		bobLukas.subscribe(s3);
		bobLukas.subscribe(s4);
		
		bobLukas.unsubscribe(s2);
	
		s1.subscribeChannel(bobLukas);
		s2.subscribeChannel(bobLukas);
		s3.subscribeChannel(bobLukas);
		s4.subscribeChannel(bobLukas);
		
		bobLukas.upload("Super Tutorial");
	}
}
