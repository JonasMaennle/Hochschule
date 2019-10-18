import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;

public class Entry {
	
	private String name;
	private float percent;
	private Polygon polygon;
	private Color color;
	private Point textPoint;
	
	public Entry(String name, float percent, ColorEntry colorEntry) {
		this.name = name;
		this.percent = percent;
		this.polygon = new Polygon();
		this.color = new Color(colorEntry.r, colorEntry.g, colorEntry.b);
		this.textPoint = new Point();
	}
	
	public void addPointToPolygon(int x, int y) {
		polygon.addPoint(x, y);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Point getTextPoint() {
		return textPoint;
	}

	public void setTextPoint(Point textPoint) {
		this.textPoint = textPoint;
	}
}
