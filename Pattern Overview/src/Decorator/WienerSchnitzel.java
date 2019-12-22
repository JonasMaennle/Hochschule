package Decorator;

public class WienerSchnitzel implements Gericht{

	@Override
	public int getPreis() {
		return 13;
	}

	@Override
	public void druckeBeschreibung() {
		System.out.print("Wiener Schnitzel");
	}
}
