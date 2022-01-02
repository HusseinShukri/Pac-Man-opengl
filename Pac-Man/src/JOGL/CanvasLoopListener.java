package JOGL;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.FPSAnimator;

import Dto.QuestionDto;
import GUI.LoseWindow;
import GUI.QuestionWindow;
import GUI.WinWindow2;
import Model.Circle;
import Model.Coin;
import Model.Ghost;
import Model.Ghost2;
import Model.Line;
import Model.Wall;
import Model.PacMan;
import Model.Point;
import Model.QuestionsList;
import Model.pacmanStatus;
import Utilitiy.AlgoUtility;
import Utilitiy.AudioUtility;
import Utilitiy.ColorsUtility;

public class CanvasLoopListener extends ClassJOGL {
	private int[] panelSize = new int[] { 0, 0 };
	private List<Wall> walls = new LinkedList<Wall>();
	private float[] wallColro = ColorsUtility.BLUE;
	private LinkedList<PacMan> lives = new LinkedList<PacMan>();
	private Ghost blueGhost;
	private Ghost2 redGhost;
	private List<Coin> coins = new LinkedList<Coin>();
	private int recoveryTime;
	private PacMan packman;
	private int liveShift = 30;
	private boolean coomand = false;
	private LinkedList<Line> lastTrack = new LinkedList<Line>();
	private JLabel scoreLabel;
	private int score = 0;
	private QuestionDto dto = new QuestionDto();
	private QuestionsList list = new QuestionsList();
	private JFrame gameWindowFrame;

	private void updateLabelScore() {
		this.scoreLabel.setText(score + "");
	}

	private void win() {
		WinWindow2 window = new WinWindow2();
		window.getFrmWinner().setVisible(true);
		this.gameWindowFrame.setVisible(false);
	}

	private void lose() {
		AudioUtility.playDeath();
		LoseWindow window = new LoseWindow();
		window.getFrmLose().setVisible(true);
		this.gameWindowFrame.setVisible(false);
	}

	public void resum() {
		if (dto.isAnsweredCorrect) {
			AudioUtility.playCorrect();
			//audio
			lives.add(new PacMan(new float[] { 1, 1, 0 }, liveShift, 16, 8, 8, pacmanStatus.right, 0));
			liveShift = updateShiftAdd(liveShift);
		} else {
			AudioUtility.playWrong();
			//audio
			this.lives.removeLast();
			liveShift = updateShiftRemove(liveShift);
			if (this.lives.isEmpty()) {
				getAnimator().stop();
				this.lose();
			}
		}
		getAnimator().resume();
	}

	private void isQuastionTime() {
		if (score != 0 && this.score % 150 == 0) {
			getAnimator().pause();
			dto.isAnsweredCorrect = false;
			dto.isAnswered = false;
			QuestionWindow window = new QuestionWindow(this, dto, list.getQuestion());
			window.getFrame().setVisible(true);

		}
	}

	private void updateTrack(int x, int y) {
		if (!this.lastTrack.isEmpty()) {
			this.lastTrack.add(new Line(this.lastTrack.getLast().getPoint2(), new Point(x, y)));
		}
	}

	private int updateShiftAdd(int shift) {
		return shift += 20;
	}

	private int updateShiftRemove(int shift) {
		return shift -= 20;
	}

	public CanvasLoopListener(JLabel scoreLabel, JFrame gameWindowFrame) {
		super();
		try {
			this.scoreLabel = scoreLabel;
			this.gameWindowFrame = gameWindowFrame;
			this.setBackgroundColor(new float[] { 0, 0, 0 });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		setGl(drawable.getGL().getGL2());
		panelSize[0] = drawable.getSurfaceWidth();
		panelSize[1] = drawable.getSurfaceHeight();
		getGl().glClearColor(getBackgroundColor()[0], getBackgroundColor()[1], getBackgroundColor()[2], 0f);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT);

		this.setAnimator(new FPSAnimator(drawable, 10, true));
		this.getAnimator().start();
		System.out.println("init size : " + panelSize[0] + " " + panelSize[1]);
		// packman
		packman = new PacMan(new float[] { 1, 1, 0 }, 230, 50, 10, 10, pacmanStatus.noDirection, 10);
		// class Ghost
		Line track1 = new Line(new Point(40, 50), new Point(40, 400));
		Line track2 = new Line(new Point(40, 400), new Point(550, 400));
		Line track3 = new Line(new Point(550, 400), new Point(550, 50));
		Line track4 = new Line(new Point(550, 50), new Point(40, 50));
		blueGhost = new Ghost(ColorsUtility.CYAN, 40, 50, 10, 10, new Line[] { track1, track2, track3, track4 }, 10);

		// Ghost2 tracks
		this.lastTrack.add(new Line(new Point(0, 0), new Point(230, 50)));
		redGhost = new Ghost2(ColorsUtility.RED, 40, 50, 10, 10, lastTrack, 5);

		// lives
		lives.add(new PacMan(new float[] { 1, 1, 0 }, liveShift, 16, 8, 8, pacmanStatus.right, 0));
		liveShift = updateShiftAdd(liveShift);
		lives.add(new PacMan(new float[] { 1, 1, 0 }, liveShift, 16, 8, 8, pacmanStatus.right, 0));
		liveShift = updateShiftAdd(liveShift);
		lives.add(new PacMan(new float[] { 1, 1, 0 }, liveShift, 16, 8, 8, pacmanStatus.right, 0));
		liveShift = updateShiftAdd(liveShift);

		// coins//226,135,67
		int xaxis = 50;
		for (int i = 0; i < 24; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, xaxis + 20, 50, 5, 5)));
			xaxis += 20;
		}
		xaxis = 50;
		for (int i = 0; i < 24; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, xaxis + 20, 400, 5, 5)));
			xaxis += 20;
		}
		xaxis = 50;
		for (int i = 0; i < 8; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, xaxis + 20, 280, 5, 5)));
			xaxis += 20;
		}
		xaxis = 50;
		for (int i = 0; i < 8; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, xaxis + 20, 180, 5, 5)));
			xaxis += 20;
		}
		xaxis = 370;
		for (int i = 0; i < 8; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, xaxis + 20, 280, 5, 5)));
			xaxis += 20;
		}
		xaxis = 370;
		for (int i = 0; i < 8; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, xaxis + 20, 180, 5, 5)));
			xaxis += 20;
		}

		int yaxis = 30;
		for (int i = 0; i < 18; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 40, yaxis + 20, 5, 5)));
			yaxis += 20;
		}
		yaxis = 30;
		for (int i = 0; i < 18; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 555, yaxis + 20, 5, 5)));
			yaxis += 20;
		}
		yaxis = 50;
		for (int i = 0; i < 16; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 290, yaxis + 20, 5, 5)));
			yaxis += 20;
		}
		yaxis = 50;
		for (int i = 0; i < 16; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 230, yaxis + 20, 5, 5)));
			yaxis += 20;
		}
		yaxis = 50;
		for (int i = 0; i < 16; i++) {
			coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 360, yaxis + 20, 5, 5)));
			yaxis += 20;
		}

		// draw map
		walls.add(new Wall(new Point(20, 30), new Point(20, 200), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(20, 200), new Point(0, 200), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(20, 250), new Point(0, 250), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(20, 250), new Point(20, 420), ColorsUtility.BLUE));//
		walls.add(new Wall(new Point(20, 420), new Point(580, 420), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(580, 420), new Point(580, 250), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(580, 250), new Point(this.panelSize[0], 250), ColorsUtility.BLUE));//
		walls.add(new Wall(new Point(580, 200), new Point(this.panelSize[0], 200), ColorsUtility.BLUE));//
		walls.add(new Wall(new Point(580, 200), new Point(580, 30), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(580, 30), new Point(20, 30), ColorsUtility.BLUE));

		// BOX1
		walls.add(new Wall(new Point(60, 70), new Point(60, 150), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(60, 70), new Point(200, 70), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(200, 70), new Point(200, 150), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(200, 150), new Point(60, 150), ColorsUtility.BLUE));
		// BOX2
		walls.add(new Wall(new Point(390, 70), new Point(390, 150), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(390, 70), new Point(530, 70), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(530, 70), new Point(530, 150), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(530, 150), new Point(390, 150), ColorsUtility.BLUE));
		// BOX3
		walls.add(new Wall(new Point(390, 300), new Point(390, 380), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(390, 300), new Point(530, 300), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(530, 300), new Point(530, 380), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(530, 380), new Point(390, 380), ColorsUtility.BLUE));
		// BOX4
		walls.add(new Wall(new Point(60, 300), new Point(60, 380), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(60, 300), new Point(200, 300), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(200, 300), new Point(200, 380), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(200, 380), new Point(60, 380), ColorsUtility.BLUE));
		// LEFT CENTER BOX
		walls.add(new Wall(new Point(60, 200), new Point(60, 260), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(60, 200), new Point(200, 200), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(200, 200), new Point(200, 260), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(200, 260), new Point(60, 260), ColorsUtility.BLUE));
		// RIGHT CENTER BOX
		walls.add(new Wall(new Point(390, 200), new Point(390, 260), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(390, 200), new Point(530, 200), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(530, 200), new Point(530, 260), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(530, 260), new Point(390, 260), ColorsUtility.BLUE));
		// CENTER WALLS
		walls.add(new Wall(new Point(260, 70), new Point(260, 380), ColorsUtility.BLUE));
		walls.add(new Wall(new Point(320, 70), new Point(320, 380), ColorsUtility.RED));
		updateLabelScore();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		super.display(drawable);
		getGl().glClearColor(getBackgroundColor()[0], getBackgroundColor()[1], getBackgroundColor()[2], 0f);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		getGl().glLoadIdentity();
		getGl().glMatrixMode(GL2.GL_PROJECTION);
		getGl().glLoadIdentity();
		getGl().glOrtho(0, panelSize[0], 0, panelSize[1], -1, 1);// left,right,bottom,top
		getGl().glMatrixMode(GL2.GL_MODELVIEW);
		getGl().glLoadIdentity();
//		getGl().glViewport(50, 50, panelSize[0], panelSize[1] ); // (x,y,width,height)

		goshtCollide();
		packman.draw(getGl());
		blueGhost.draw(getGl());
		if (redGhost.draw(getGl(), this.lastTrack)) {
			if (!this.lastTrack.isEmpty()) {
				Line lastLine = this.lastTrack.getLast();
				this.lastTrack.clear();
				this.lastTrack.add(lastLine);
			}
		}
		for (Coin coin : coins) {
			coin.getCircle().draw(getGl());
		}
		coinsCollide();

		for (Wall line : walls) {
			AlgoUtility.drowLines_STRIP(getGl(), new float[] { 0, 0, 1 }, line, 3);
		}

		for (PacMan live : lives) {
			live.draw(getGl());
		}
		if (coomand) {
			coomand = false;
			if (packman.getStatus() == pacmanStatus.up) {
				if (packman.up(getGl(), wallColro)) {
					updateTrack(packman.getCenterX(), packman.getCenterY());
				}
			} else if (packman.getStatus() == pacmanStatus.down) {
				if (packman.down(getGl(), wallColro)) {
					updateTrack(packman.getCenterX(), packman.getCenterY());
				}
			} else if (packman.getStatus() == pacmanStatus.left) {
				if (packman.left(getGl(), wallColro)) {
					updateTrack(packman.getCenterX(), packman.getCenterY());
				}
			} else if (packman.getStatus() == pacmanStatus.right) {
				if (packman.right(getGl(), wallColro)) {
					updateTrack(packman.getCenterX(), packman.getCenterY());
				}
			}
		}
		if (recoveryTime > 0) {
			this.recoveryTime--;
		}

		enterPortalPack();
	}

	private void enterPortalPack() {
		if (this.packman.getCenterX() < 0) {
			this.packman.setCenterX(this.panelSize[0]);
			System.out.println("Evet : Enter West Portal");
		} else if (this.packman.getCenterX() > this.panelSize[0]) {
			this.packman.setCenterX(0);
			System.out.println("Evet : Enter East Portal");
		}
	}

	private void coinsCollide() {
		for (int i = 0; i < coins.size(); i++) {
			if (isCollide(packman.getCenterX(), packman.getCenterY(), packman.getRadius(), coins.get(i).getX(),
					coins.get(i).getY())) {
				coins.remove(i);
				if (this.score % 30 == 0) {
					AudioUtility.playEatCoin();
				}
				;
				this.score += 5;
				updateLabelScore();
				isQuastionTime();
			}
		}
		if (coins.isEmpty()) {
			getAnimator().stop();
			this.win();
		}
	}

	private boolean goshtCollide() {
		if (isCollide(packman.getCenterX(), packman.getCenterY(), packman.getRadius() * 2, blueGhost.getCenterX(),
				blueGhost.getCenterY())) {
			if (recoveryTime <= 0) {
				recoveryTime = 50;
				this.lives.removeLast();
				liveShift = updateShiftRemove(liveShift);
				packman.setNoDirection();
				packman.draw(getGl());
				if (this.lives.isEmpty()) {
					getAnimator().stop();
					this.lose();
				} else {
					AudioUtility.playLiveLost();
				}
			}
		} else if (isCollide(packman.getCenterX(), packman.getCenterY(), packman.getRadius() * 2, redGhost.getCenterX(),
				redGhost.getCenterY())) {
			this.lives.clear();
			packman.setNoDirection();
			getAnimator().stop();
			this.lose();
		}
		return false;
	}

	private boolean isCollide(int pacmanX, int pacmanY, int pacmanRange, int coinX, int coinY) {
		int[] size = new int[] { pacmanX - pacmanRange, pacmanX + pacmanRange, pacmanY + pacmanRange,
				pacmanY - pacmanRange };// left,right,top,bottom
		return (size[0] < coinX && size[1] > coinX) && (size[2] > coinY && coinY > size[3]);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == java.awt.event.KeyEvent.VK_W) {
			coomand = true;
			this.packman.setStatus(pacmanStatus.up);
		} else if (e.getKeyChar() == java.awt.event.KeyEvent.VK_S) {
			coomand = true;
			this.packman.setStatus(pacmanStatus.down);
		} else if (e.getKeyChar() == java.awt.event.KeyEvent.VK_A) {
			coomand = true;
			this.packman.setStatus(pacmanStatus.left);
		} else if (e.getKeyChar() == java.awt.event.KeyEvent.VK_D) {
			coomand = true;
			this.packman.setStatus(pacmanStatus.right);
		}
	}

}
