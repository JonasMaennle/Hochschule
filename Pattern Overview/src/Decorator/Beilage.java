package Decorator;

public abstract class Beilage implements Gericht{
	Gericht gericht;
	
	public Beilage(Gericht gericht) {
		this.gericht = gericht;
	}
}
