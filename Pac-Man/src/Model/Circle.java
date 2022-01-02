package Model;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;

import Utilitiy.AlgoUtility;
import Utilitiy.DrawingUtility;
import Utilitiy.MatricesUtility;

public class Circle {
	private int centerX;
	private int centerY;
	private int radius;
	private List<List<int[][]>> points;
	private float[] color;
	private int layers;
	private pacmanStatus status;

	/**
	 * 
	 * @param color
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @param layers
	 */
	public Circle(float[] color, int centerX, int centerY, int radius, int layers) {
		super();
		this.color = color;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.layers = layers;
		this.status = pacmanStatus.noDirection;
		this.points = new LinkedList<List<int[][]>>();
		new Thread(() -> {
			this.points = initPoints(pacmanStatus.noDirection,this.layers);
		}).start();
	}

	public Circle(float[] color, int centerX, int centerY, int radius) {
		super();
		this.color = color;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.layers = 1;
		this.status = pacmanStatus.noDirection;
		new Thread(() -> {
			this.points = initPoints(pacmanStatus.noDirection,this.layers);
		}).start();
	}

	private void setStatus(pacmanStatus pacmanStatus) {
		this.status = pacmanStatus;
	}

	public void left() {
		if (this.status != pacmanStatus.left) {
			setStatus(pacmanStatus.left);
			this.points = initPoints(pacmanStatus.left,layers);
		}
		move(-5, 0);
	}

	public void right() {
		if (this.status != pacmanStatus.right) {
			setStatus(pacmanStatus.right);
			this.points = initPoints(pacmanStatus.right,layers);
		}
		move(5, 0);
	}

	public void up() {
		if (this.status != pacmanStatus.up) {
			setStatus(pacmanStatus.up);
			this.points = initPoints(pacmanStatus.up,layers);
		}
		move(0, 5);
	}

	public void down() {
		if (this.status != pacmanStatus.down) {
			setStatus(pacmanStatus.down);
			this.points = initPoints(pacmanStatus.down,layers);
		}
		move(0, -5);
	}

	public void move(int x, int y) {
		this.centerX += x;
		this.centerY += y;
		MatricesUtility.pixelsShifte(points, x, y);
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getRadius() {
		return radius;
	}

	public List<List<int[][]>> getPoints() {
		return points;
	}

	public void draw(GL2 gl) {
		for (List<int[][]> list : this.points) {
			for (int[][] is : list) {
				for (int[] is2 : is) {
					DrawingUtility.setPixelColor(gl, this.color, is2[0], is2[1]);
				}
			}
		}
	}

	public void draw(GL2 gl, float[] color) {
		for (List<int[][]> list : this.points) {
			for (int[][] is : list) {
				for (int[] is2 : is) {
					DrawingUtility.setPixelColor(gl, color, is2[0], is2[1]);
				}
			}
		}
	}

	private List<List<int[][]>> initPoints(pacmanStatus status, int layers) {
		List<List<int[][]>> points = new LinkedList<List<int[][]>>();
		if (status == pacmanStatus.noDirection) {
			for (int i = 0; i < layers; i++) {
				points.add(AlgoUtility.midPointCircle(this.centerX, this.centerY, radius - i));
			}
		} else if (status == pacmanStatus.up) {
			for (int i = 0; i < layers; i++) {
				points.add(AlgoUtility.midPointCircleUp(this.centerX, this.centerY, radius - i));
			}
		} else if (status == pacmanStatus.down) {
			for (int i = 0; i < layers; i++) {
				points.add(AlgoUtility.midPointCircleDown(this.centerX, this.centerY, radius - i));
			}
		} else if (status == pacmanStatus.left) {
			for (int i = 0; i < layers; i++) {
				points.add(AlgoUtility.midPointCircleLeft(this.centerX, this.centerY, radius - i));
			}
		} else if (status == pacmanStatus.right) {
			for (int i = 0; i < layers; i++) {
				points.add(AlgoUtility.midPointCircleRight(this.centerX, this.centerY, radius - i));
			}
		}

		return points;
	}

}