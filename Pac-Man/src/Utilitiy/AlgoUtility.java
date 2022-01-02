package Utilitiy;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;

import Model.Wall;

public class AlgoUtility {
	
	public static void drowLines_STRIP(GL2 gl, float[] rgb, int[][] matrix,int thickness) {
		for (int th = 0; th < thickness; th++) {
			for (int i = 0; i < matrix.length - 1; i++) {
				AlgoUtility.bressenham(gl, rgb, matrix[i][0]+th, matrix[i][1], matrix[i + 1][0]+th, matrix[i + 1][1]);
			}
		}
	}
	
	public static void drowLines_STRIP(GL2 gl, float[] rgb, Wall line,int thickness) {
		for (int th = 0; th < thickness; th++) {
				AlgoUtility.bressenham(gl, rgb, line.getPoint1().getX()+th, line.getPoint1().getY(),line.getPoint2().getX()+th, line.getPoint2().getY());
		}
	}

	public static void drowLines_STRIP(GL2 gl, float[] rgb, int[][] matrix) {
		for (int i = 0; i < matrix.length - 1; i++) {
			AlgoUtility.bressenham(gl, rgb, matrix[i][0], matrix[i][1], matrix[i + 1][0], matrix[i + 1][1]);
		}
	}

	public static void drowLines_LOOP(GL2 gl, float[] rgb, int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			if (i == (matrix.length - 1)) {
				AlgoUtility.bressenham(gl, rgb, matrix[i][0], matrix[i][1], matrix[0][0], matrix[0][1]);
			} else {
				AlgoUtility.bressenham(gl, rgb, matrix[i][0], matrix[i][1], matrix[i + 1][0], matrix[i + 1][1]);
			}
		}
	}

	public static void bressenham(GL2 gl, float[] color, int xa, int ya, int xb, int yb) {
		int dx = Math.abs(xa - xb);
		int dy = Math.abs(ya - yb);
		int p = 2 * dy - dx;
		int towDy = 2 * dy;
		int towDyDx = 2 * (dy - dx);
		int x, y;
		if (xa != xb) {
			int xEnd;
			if (xa > xb) {
				x = xb;
				y = yb;
				xEnd = xa;
			} else {
				x = xa;
				y = ya;
				xEnd = xb;
			}
			DrawingUtility.setPixelColor(gl, color, x, y);
			while (x < xEnd) {
				x += 1;
				if (p < 0) {
					p += towDy;
				} else {
					y += 1;
					p += towDyDx;
				}
				DrawingUtility.setPixelColor(gl, color, x, y);
			}
		} else {
			int yEnd;
			if (ya > yb) {
				x = xb;
				y = yb;
				yEnd = ya;
			} else {
				x = xa;
				y = ya;
				yEnd = yb;
			}
			DrawingUtility.setPixelColor(gl, color, x, y);
			while (y < yEnd) {
				y += 1;
				DrawingUtility.setPixelColor(gl, color, x, y);
			}
		}
	}

	public static List<int[][]> midPointCircle(int xCenter, int yCenter, int radius) {
		List<int[][]> points = new LinkedList<int[][]>();
		int x = 0;
		int y = radius;
		int p = 1 - radius;
		points.add(circlePlotePoints(xCenter, yCenter, x, y));
		while (x < y) {
			x++;
			if (p < 0) {
				p += 2 * x + 1;
			} else {
				y -= 1;
				p += 2 * (x - y) + 1;
			}
			points.add(circlePlotePoints(xCenter, yCenter, x, y));
		}
		return points;
	}

	public static int[][] circlePlotePoints(int xCenter, int yCenter, int x, int y) {
		return new int[][] { new int[] { xCenter + x, yCenter + y }, new int[] { xCenter - x, yCenter + y }, // up
				new int[] { xCenter + x, yCenter - y }, new int[] { xCenter - x, yCenter - y }, // down
				new int[] { xCenter + y, yCenter + x }, new int[] { xCenter + y, yCenter - x }, // left
				new int[] { xCenter - y, yCenter + x }, new int[] { xCenter - y, yCenter - x } // right
		};
	}
	
	public static List<int[][]> midPointCircleUp(int xCenter, int yCenter, int radius) {
		List<int[][]> points = new LinkedList<int[][]>();
		int x = 0;
		int y = radius;
		int p = 1 - radius;
		points.add(circlePlotePointsUp(xCenter, yCenter, x, y));
		while (x < y) {
			x++;
			if (p < 0) {
				p += 2 * x + 1;
			} else {
				y -= 1;
				p += 2 * (x - y) + 1;
			}
			points.add(circlePlotePointsUp(xCenter, yCenter, x, y));
		}
		return points;
	}


	public static int[][] circlePlotePointsUp(int xCenter, int yCenter, int x, int y) {
		return new int[][] {
				new int[] { xCenter + x, yCenter - y }, new int[] { xCenter - x, yCenter - y }, // down
				new int[] { xCenter + y, yCenter + x }, new int[] { xCenter + y, yCenter - x }, // left
				new int[] { xCenter - y, yCenter + x }, new int[] { xCenter - y, yCenter - x } // right
		};
	}
	
	public static List<int[][]> midPointCircleDown(int xCenter, int yCenter, int radius) {
		List<int[][]> points = new LinkedList<int[][]>();
		int x = 0;
		int y = radius;
		int p = 1 - radius;
		points.add(circlePlotePointsDown(xCenter, yCenter, x, y));
		while (x < y) {
			x++;
			if (p < 0) {
				p += 2 * x + 1;
			} else {
				y -= 1;
				p += 2 * (x - y) + 1;
			}
			points.add(circlePlotePointsDown(xCenter, yCenter, x, y));
		}
		return points;
	}

	public static int[][] circlePlotePointsDown(int xCenter, int yCenter, int x, int y) {
		return new int[][] { new int[] { xCenter + x, yCenter + y }, new int[] { xCenter - x, yCenter + y }, // up
				new int[] { xCenter + y, yCenter + x }, new int[] { xCenter + y, yCenter - x }, // left
				new int[] { xCenter - y, yCenter + x }, new int[] { xCenter - y, yCenter - x } // right
		};
	}

	
	public static List<int[][]> midPointCircleLeft(int xCenter, int yCenter, int radius) {
		List<int[][]> points = new LinkedList<int[][]>();
		int x = 0;
		int y = radius;
		int p = 1 - radius;
		points.add(circlePlotePointsLeft(xCenter, yCenter, x, y));
		while (x < y) {
			x++;
			if (p < 0) {
				p += 2 * x + 1;
			} else {
				y -= 1;
				p += 2 * (x - y) + 1;
			}
			points.add(circlePlotePointsLeft(xCenter, yCenter, x, y));
		}
		return points;
	}
	public static int[][] circlePlotePointsLeft(int xCenter, int yCenter, int x, int y) {
		return new int[][] { new int[] { xCenter + x, yCenter + y }, new int[] { xCenter - x, yCenter + y }, // up
				new int[] { xCenter + x, yCenter - y }, new int[] { xCenter - x, yCenter - y }, // down
				new int[] { xCenter + y, yCenter + x }, new int[] { xCenter + y, yCenter - x }, // left
		};
	}

	
	public static List<int[][]> midPointCircleRight(int xCenter, int yCenter, int radius) {
		List<int[][]> points = new LinkedList<int[][]>();
		int x = 0;
		int y = radius;
		int p = 1 - radius;
		points.add(circlePlotePointsRight(xCenter, yCenter, x, y));
		while (x < y) {
			x++;
			if (p < 0) {
				p += 2 * x + 1;
			} else {
				y -= 1;
				p += 2 * (x - y) + 1;
			}
			points.add(circlePlotePointsRight(xCenter, yCenter, x, y));
		}
		return points;
	}
	public static int[][] circlePlotePointsRight(int xCenter, int yCenter, int x, int y) {
		return new int[][] { new int[] { xCenter + x, yCenter + y }, new int[] { xCenter - x, yCenter + y }, // up
				new int[] { xCenter + x, yCenter - y }, new int[] { xCenter - x, yCenter - y }, // down
				new int[] { xCenter - y, yCenter + x }, new int[] { xCenter - y, yCenter - x } // right
		};
	}

}
