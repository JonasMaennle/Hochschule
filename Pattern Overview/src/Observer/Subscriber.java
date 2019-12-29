package Observer;

public class Subscriber implements Observer {
	
	private String name;
	private Channel channel;
	
	public Subscriber(String name) {
		this.name = name;
	}
	
	@Override
	public void update() {
		System.out.println("Hey " + name + " a Video was uploaded! : " + channel.getTitle());
	}
	
	@Override
	public void subscribeChannel(Channel chanel) {
		this.channel = chanel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
