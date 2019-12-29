package Observer;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Subject {

	List<Subscriber> subs;
	private String title;
	
	public Channel() {
		this.subs = new ArrayList<Subscriber>();
	}
	
	@Override
	public void subscribe(Subscriber subscriber) {
		subs.add(subscriber);
	}
	
	@Override
	public void unsubscribe(Observer subscriber) {
		subs.remove(subscriber);
	}
	
	@Override
	public void notifySubs() {
		for(Observer subscriber : subs)
			subscriber.update();
	}
	
	@Override
	public void upload(String title) {
		this.title = title;
		notifySubs();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
