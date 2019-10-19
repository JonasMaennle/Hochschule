import java.util.ArrayList;

import javax.swing.JFrame;

public class Window extends JFrame{

	private static final long serialVersionUID = 5259700796854880162L;
	private final int width = 800, height = 800;

	public Window() {
		setTitle("Premium Pie Chart");
		setSize(width, height);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		ArrayList<Entry> entryList = new ArrayList<Entry>();

		entryList.add(new Entry("Value 1", 15.6f, ColorEntry.blue));
		entryList.add(new Entry("Value 2", 19.5f, ColorEntry.orange));
		entryList.add(new Entry("Value 3", 8.9f, ColorEntry.grey));
		entryList.add(new Entry("Value 4", 14.0f, ColorEntry.brown));
		entryList.add(new Entry("Value 5", 10.0f, ColorEntry.berry));
		entryList.add(new Entry("Value 6", 11.4f, ColorEntry.purple));
		entryList.add(new Entry("Value 7", 6.1f, ColorEntry.turquoise));
		entryList.add(new Entry("Value 8", 14.5f, ColorEntry.green));
		
		add(new Pie(width, height, entryList));
	}
	
	public static void main(String[] args) {
		new Window();
	}
}
