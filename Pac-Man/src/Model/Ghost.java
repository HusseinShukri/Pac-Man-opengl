package Model;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;

import Utilitiy.AlgoUtility;
import Utilitiy.DrawingUtility;
import Utilitiy.MatricesUtility;

public class Ghost {

	private int centerX;
	private int centerY;
	private int radius;
	private List<List<int[][]>> points;
	private float[] color;
	private int layers;
	private Line[] track;
	private int currentTrack;
	private boolean newTrack;
	private int speed;
	private boolean xOperation;
	private boolean yOperation;

	/**
	 * 
	 * @param color
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @param layers
	 * @param track
	 * @param speed
	 */
	public Ghost(float[] color, int centerX, int centerY, int radius, int layers, Line[] track, int speed) {
		super();
		this.color = color;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.layers = layers;
		this.track = track;
		this.currentTrack = 0;
		this.speed = speed;
		decideOperation(track[0].getPoint1(), track[0].getPoint2());
		this.points = new LinkedList<List<int[][]>>();
		new Thread(() -> {
			this.points = initPoints(pacmanStatus.noDirection, this.layers);
		}).start();
	}

	private void decideOperation(Point p1, Point p2) {
		if (p2.getX() - p1.getX() > 0) { // positive x
			this.xOperation = true;
		} else {// negative x
			this.xOperation = false;
		}
		if (p2.getY() - p1.getY() > 0) { // positive y
			this.yOperation = true;
		} else {// negative y
			this.yOperation = false;
		}
	}

	public void draw(GL2 gl) {
		if (currentTrack == this.track.length-1 && newTrack) {
			newTrack = false;
			currentTrack = 0;
			decideOperation(track[currentTrack].getPoint1(), track[currentTrack].getPoint2());
			System.out.println("reset track");
		} else if (newTrack) {
			newTrack = false;
			currentTrack++;
			decideOperation(track[currentTrack].getPoint1(), track[currentTrack].getPoint2());
			System.out.println("new track");
		}

		if (this.centerX != track[currentTrack].getPoint2().getX()
				|| this.centerY != track[currentTrack].getPoint2().getY()) {
			if (this.centerX != track[currentTrack].getPoint2().getX()) {
				if (xOperation) {
					move(speed, 0);
				} else {
					move(-speed, 0);
				}
			} else if (this.centerY != track[currentTrack].getPoint2().getY()) {
				if (yOperation) {
					move(0, speed);
				} else {
					move(0, -speed);
				}

			}
		} else {
			newTrack = true;
		}
		drawShape(gl);
	}

	public void move(int x, int y) {
		this.centerX += x;
		this.centerY += y;
		MatricesUtility.pixelsShifte(points, x, y);
	}

	private void drawShape(GL2 gl) {
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

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}
	
	
}
