package Observer;

public interface Subject {

	void subscribe(Subscriber subscriber);
	void unsubscribe(Observer subscriber);
	void notifySubs();
	void upload(String title);
}