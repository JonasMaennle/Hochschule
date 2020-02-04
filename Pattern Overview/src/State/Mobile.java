package State;

public class Mobile {
	MobileAlertState mobileAlertState;
	
	public Mobile() {
		this.mobileAlertState = new Ringing();
	}
	
	public void alert() {
		this.mobileAlertState.alert();
	}
	
	public void setState(MobileAlertState state) {
		this.mobileAlertState = state;
	}
}
