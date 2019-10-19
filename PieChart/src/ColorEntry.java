
public enum ColorEntry {

	orange(255,153,0),
	brown(77, 51, 25),
	grey(30,30,30),
	blue(0, 102, 255),
	green(170, 255, 5),
	berry(134, 45, 89),
	turquoise(0, 204, 204),
	purple(102, 0, 204);
	
	public int r,g,b;
	
	ColorEntry(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
}
