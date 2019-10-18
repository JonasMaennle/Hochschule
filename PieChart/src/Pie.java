import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Pie extends JPanel{

	private static final long serialVersionUID = -3075918995360679751L;
	private int width, height;
	private Point center;
	private int radius;
	private ArrayList<Entry> entryList;
	private float degreeOffset;
	private final int TEXT_OFFSET = 75;

	public Pie(int width, int height, ArrayList<Entry> entryList) {
		this.width = width;
		this.height = height;
		
		this.center = new Point(width / 2 - 50, height / 2 - 50);
		this.radius = (width - 300) / 2;
		
		this.degreeOffset = 0;
		this.entryList = entryList;
	}
	
	public void paint(Graphics g) {
		// set background color and draw background
		g.setColor(new Color(51, 51, 51));
		g.fillRect(0, 0, width, height);
		
		// custom font
		Font font = new Font("Arial", Font.BOLD, 22);
		g.setFont(font);
		
		// headline
		g.setColor(Color.WHITE);
		g.drawString("Premium Pie Chart", width / 2 - 120, 50);

		// draw all entries
		for(Entry current : entryList) {
			if(degreeOffset <= 360) {
				degreeOffset += current.getPercent();
				fillAndDrawPolygon(current, degreeOffset, g);
			}
		}
		
		// draw text
		g.setColor(Color.white);
		for(Entry entry : entryList) {
			g.drawString(entry.getName() + ", " + entry.getPercent() + "%", (int)entry.getTextPoint().getX(), (int)entry.getTextPoint().getY());
		}
	}
	
	private void setTextPosition(float degree, Entry entry, Graphics g) {
		degree = calcAngleInDegree(degree  - entry.getPercent()/2);
		float x = (float) (Math.cos(degree) * radius);
		float y = (float) (Math.sin(degree) * radius);
		entry.setTextPoint(new Point((int)(center.getX() + x) - TEXT_OFFSET, (int)(center.getY() + y)));
	}
	
	private float calcAngleInDegree(float percent) {
		float angle = (3.6f * percent) - 90;
		return (float) Math.toRadians(angle);
	}
	
	private void fillAndDrawPolygon(Entry entry, float degree, Graphics g) {
		for(float i = (int)(degree - entry.getPercent()); i <= degree; i += 0.05) {
			float tmpDegree = calcAngleInDegree(i);
			float x = (float) (Math.cos(tmpDegree) * radius);
			float y = (float) (Math.sin(tmpDegree) * radius);
			entry.addPointToPolygon((int)(center.getX() + x), (int)(center.getY() + y));
		}
		entry.addPointToPolygon((int)center.getX(), (int)center.getY());
		
		g.setColor(entry.getColor());
		g.fillPolygon(entry.getPolygon());
		
		setTextPosition(degree, entry, g);
	}
}
