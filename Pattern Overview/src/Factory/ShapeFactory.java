package Factory;


public class ShapeFactory {
	
	public Shape getShape(String shape) {
		if(shape == null)
			return null;
		
		if(shape.equals("circle"))
			return new Circle();
		if(shape.equals("rectangle"))
			return new Rectangle();
		
		return null;
	}
}
