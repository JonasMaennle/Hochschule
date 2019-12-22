package Decorator;

public class Salat extends Beilage{

	public Salat(Gericht gericht) {
		super(gericht);
	}

	@Override
	public int getPreis() {
		return gericht.getPreis() + 5;
	}

	@Override
	public void druckeBeschreibung() {
		gericht.druckeBeschreibung();
		System.out.print(", Salat");
	}

}
