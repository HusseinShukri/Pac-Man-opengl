package Model;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;

import Utilitiy.AlgoUtility;
import Utilitiy.DrawingUtility;
import Utilitiy.MatricesUtility;

public class Ghost2 {

	private int centerX;
	private int centerY;
	private int radius;
	private List<List<int[][]>> points;
	private float[] color;
	private int layers;
	
	private List<Line> tracks;
	private int currentTrack;
	private boolean newTrack;
	private int speed;
	private boolean xOperation;
	private boolean yOperation;
	private boolean mode2 = false;

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
	public Ghost2(float[] color, int centerX, int centerY, int radius, int layers, List<Line> track, int speed) {
		super();
		this.color = color;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.layers = layers;
		this.tracks = track;
		this.currentTrack = 0;
		this.speed = speed;
		decideOperation(track.get(0).getPoint1(), track.get(0).getPoint2());
		this.points = new LinkedList<List<int[][]>>();
		new Thread(() -> {
			this.points = initPoints(this.layers);
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

	public boolean draw(GL2 gl, List<Line> NTrack) {
		if (NTrack.isEmpty()) {
			return false;
		}
		if (currentTrack == this.tracks.size() - 1 && newTrack) {
			newTrack = false;
			currentTrack = 0;
			mode2 = true;
			System.out.println("Event : Ghost Red Get New Tracks");
//			System.out.println("Event : Ghost Red End initial Tracks & Start Following Pac Man");
		} else if (newTrack) {
			newTrack = false;
			currentTrack++;
			decideOperation(tracks.get(currentTrack).getPoint1(), tracks.get(currentTrack).getPoint2());
			System.out.println("Event : Ghost Red Get New Track");
		}

		if (mode2) {
			this.tracks.clear();
			this.tracks.addAll(NTrack);
			mode2 = false;
			return true;

		} else {
			if (this.centerX != tracks.get(currentTrack).getPoint2().getX()
					|| this.centerY != tracks.get(currentTrack).getPoint2().getY()) {
				if (this.centerX != tracks.get(currentTrack).getPoint2().getX()) {
					if (xOperation) {
						move(speed, 0);
					} else {
						move(-speed, 0);
					}
				} else if (this.centerY != tracks.get(currentTrack).getPoint2().getY()) {
					if (yOperation) {
						move(0, speed);
					} else {
						move(0, -speed);
					}

				}
			} else {
				newTrack = true;
			}
		}
		drawShape(gl);
		return false;
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

	private List<List<int[][]>> initPoints(int layers) {
		List<List<int[][]>> points = new LinkedList<List<int[][]>>();
		for (int i = 0; i < layers; i++) {
			points.add(AlgoUtility.midPointCircle(this.centerX, this.centerY, radius - i));
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
