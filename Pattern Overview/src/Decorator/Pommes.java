package Decorator;

public class Pommes extends Beilage{

	public Pommes(Gericht gericht) {
		super(gericht);
	}

	@Override
	public int getPreis() {
		return gericht.getPreis() + 3;
	}

	@Override
	public void druckeBeschreibung() {
		gericht.druckeBeschreibung();
		System.out.print(", Pommes");
	}
}
