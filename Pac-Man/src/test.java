import Model.Line;
import Model.Point;

public class test {
	public static void main(String[] args) {
		Line l1 = new Line(new Point(10, 10), new Point(100, 10), null);
		Line l2 = new Line(new Point(50, 20), new Point(50, 5), null);
		checkRange(l1.getPoint1(), l1.getPoint2(), l2.getPoint1(), l2.getPoint2());
		System.out.println("END");
	}

	private static void checkRange(Point LP1, Point LP2, Point BP1, Point BP2) {
		if ((
				(LP1.getY() < BP1.getY() && LP2.getY() < BP1.getY())
				|| 
				(LP1.getY() > BP1.getY() && LP2.getY() > BP2.getY())
				) && (
				(LP1.getY() < BP2.getY() && LP2.getY() < BP2.getY())
				||
				(LP1.getY() > BP2.getY() && LP2.getY() > BP2.getY())
				)) {
			System.out.println("intersect");
		} 

		////
//		if (BP1.getY() > LP1.getY() && BP1.getY() < LP2.getY()) {
//			System.out.println("intersect");
//		} else if (BP1.getY() < LP1.getY() && BP1.getY() > LP2.getY()) {
//			System.out.println("intersect");
//		} else if (BP1.getX() < LP1.getX() && BP1.getX() > LP2.getX()) {
//			System.out.println("intersect");
//		} else if (BP1.getX() > LP1.getX() && BP1.getX() < LP2.getX()) {
//			System.out.println("intersect");
//		}
	}

	private static boolean isTwoLineIntersect(int X1, int X2, int X3, int X4, int Y1, int Y2, int Y3, int Y4) {
		float denominater = (X1 - X2) * (Y3 - Y4) - (Y1 - Y2) * (X3 - X4);
		if (denominater == 0) {
			System.out.print(" parallel ");
			// no cross both parallel
			return false;
		}
		float T = ((X1 - X3) * (Y3 - Y4) - (Y1 - Y3) * (X3 - X4)) / denominater;
		float U = -((X1 - X2) * (Y1 - Y3) - (Y1 - Y2) * (X1 - X3)) / denominater;
		// cross when 0 < t < 1 , u > 0
		if (0 < T && T < 1 && U > 0) {
			// CROSS
			return true;
		}
		return false;
	}
}
