package Decorator;

public class Nudeln extends Beilage{

	public Nudeln(Gericht gericht) {
		super(gericht);
	}

	@Override
	public int getPreis() {
		return gericht.getPreis() + 3;
	}

	@Override
	public void druckeBeschreibung() {
		gericht.druckeBeschreibung();
		System.out.print(", Nudeln");
	}

}
