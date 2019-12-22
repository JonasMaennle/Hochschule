package Decorator;

public class Test {

	public static void main(String[] args) {
		Gericht gericht = new Salat(new Nudeln(new Pommes(new WienerSchnitzel())));
	
		System.out.println(gericht.getPreis());
		gericht.druckeBeschreibung();
	}
}
