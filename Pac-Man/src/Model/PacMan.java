package Model;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL2;

import Utilitiy.AlgoUtility;
import Utilitiy.DrawingUtility;
import Utilitiy.MatricesUtility;

public class PacMan {
	private int centerX;
	private int centerY;
	private int radius;
	private List<List<int[][]>> points;
	private float[] color;
	private int layers;
	private pacmanStatus status;
	private boolean statusFlage = false;
	private int speed;

	/**
	 * 
	 * @param color
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @param layers
	 * @param status
	 */
	public PacMan(float[] color, int centerX, int centerY, int radius, int layers, pacmanStatus status, int speed) {
		super();
		this.color = color;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		this.layers = layers;
		this.status = pacmanStatus.noDirection;
		this.speed = speed;
		this.points = new LinkedList<List<int[][]>>();
		new Thread(() -> {
			this.points = initPoints(status, this.layers);
		}).start();
	}

	private boolean checkMove(GL2 gl, float[] stopColor, pacmanStatus status) {
		if (pacmanStatus.up == status) {
			for (int y = this.centerY + radius; y < this.centerY + (radius * 2); y++) {// lines to test
				for (int x = this.centerX - radius; x < this.centerX + radius; x++) { // line pixels
					if (MatricesUtility.isArraysEqual(stopColor, DrawingUtility.getPixelColor(gl, x, y))) {
						return true;
					}
				}
			}

		} else if (pacmanStatus.down == status) {
			for (int y = this.centerY - radius; y > this.centerY - (radius * 2); y--) {// lines to test
				for (int x = this.centerX - radius; x < this.centerX + radius; x++) {
					if (MatricesUtility.isArraysEqual(stopColor, DrawingUtility.getPixelColor(gl, x, y))) {
						return true;
					}
				}
			}

		} else if (pacmanStatus.left == status) {
			for (int x = this.centerX - radius; x > this.centerX - (radius * 2); x--) {// lines to test
				for (int y = this.centerY - radius; y < this.centerY + radius; y++) {
					if (MatricesUtility.isArraysEqual(stopColor, DrawingUtility.getPixelColor(gl, x, y))) {
						return true;
					}
				}
			}

		} else if (pacmanStatus.right == status) {
			for (int x = this.centerX + radius; x < this.centerX + (radius * 2); x++) {// lines to test
				for (int y = this.centerY - radius; y < this.centerY + radius; y++) {
					if (MatricesUtility.isArraysEqual(stopColor, DrawingUtility.getPixelColor(gl, x, y))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public void setStatus(pacmanStatus pacmanStatus) {
		this.status = pacmanStatus;
		this.statusFlage = true;
	}

	public void setNoDirection() {
		this.status = pacmanStatus.noDirection;
		this.points = initPoints(status, layers);

	}

	public boolean left(GL2 gl, float[] Color) {
		if (statusFlage) {
			statusFlage = false;
			this.points = initPoints(pacmanStatus.left, layers);
		}
		if (!checkMove(gl, Color, pacmanStatus.left)) {
			move(-speed, 0);
		}
		return !statusFlage;
	}

	public boolean right(GL2 gl, float[] Color) {
		if (statusFlage) {
			statusFlage = false;
			this.points = initPoints(pacmanStatus.right, layers);
		}
		if (!checkMove(gl, Color, pacmanStatus.right)) {
			move(speed, 0);
		}
		return !statusFlage;
	}

	public boolean up(GL2 gl, float[] Color) {
		if (statusFlage) {
			statusFlage = false;
			this.points = initPoints(pacmanStatus.up, layers);
		}
		if (!checkMove(gl, Color, pacmanStatus.up)) {
			move(0, speed);
		}
		return !statusFlage;
	}

	public boolean down(GL2 gl, float[] Color) {
		if (statusFlage) {
			statusFlage = false;
			this.points = initPoints(pacmanStatus.down, layers);
		}
		if (!checkMove(gl, Color, pacmanStatus.down)) {
			move(0, -speed);
		}
		return !statusFlage;
	}

	public void move(int x, int y) {
		this.centerX += x;
		this.centerY += y;
		MatricesUtility.pixelsShifte(points, x, y);
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

	public float[] getColor() {
		return color;
	}

	public int getLayers() {
		return layers;
	}

	public pacmanStatus getStatus() {
		return status;
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

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

}
