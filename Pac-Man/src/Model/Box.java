package Model;

public class Box {

	private Line[] lines;

	public Box(Point p1, Point p2, Point p3, Point p4, float[] color) {
		super();
		lines = new Line[4];
		lines[0] = new Line(p1, p2, color); // :
		lines[1] = new Line(p2, p3, color);// ''
		lines[2] = new Line(p3, p4, color);// :
		lines[3] = new Line(p4, p1, color);// ...
	}
	
	

	public Line getLine(int index) {
		return this.lines[index];
	}

	public int getMaxX() {
		return this.lines[0].getPoint1().getX();
	}

	public int getminX() {
		return this.lines[2].getPoint1().getX();
	}

	public int getMaxY() {
		return this.lines[2].getPoint1().getY();
	}

	public int getMinY() {
		return this.lines[0].getPoint1().getY();
	}
}
