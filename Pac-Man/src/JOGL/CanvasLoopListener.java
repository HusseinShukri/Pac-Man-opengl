package JOGL;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.FPSAnimator;

import Model.Circle;
import Model.Coin;
import Model.Ghost;
import Model.Line;
import Model.PacMan;
import Model.Point;
import Model.pacmanStatus;
import Utilitiy.AlgoUtility;
import Utilitiy.ColorsUtility;

public class CanvasLoopListener extends ClassJOGL {
	private int[] panelSize = new int[] { 0, 0 };
	private List<Line> walls = new LinkedList<Line>();
	private LinkedList<PacMan> lives = new LinkedList<PacMan>();
	private float[] colro = ColorsUtility.BLUE;
	private Ghost ghost;
	private List<Coin> coins = new LinkedList<Coin>();
	private int recoveryTime;
	private PacMan packman;
	private int liveShift = 30;
	private boolean coomand = false;

	private int updateShift(int shift) {
		return shift += 20;
	}

	public CanvasLoopListener() {
		super();
		try {
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
		packman = new PacMan(new float[] { 1, 1, 0 }, 250, 250, 10, 10, pacmanStatus.noDirection,10);
		// class Ghost
		Line track1 = new Line(new Point(40, 50), new Point(40, 400), null);
		Line track2 = new Line(new Point(40, 400), new Point(550, 400), null);
		Line track3 = new Line(new Point(550, 400), new Point(550, 50), null);
		Line track4 = new Line(new Point(550, 50), new Point(40, 50), null);
		ghost= new Ghost(ColorsUtility.CYAN, 40, 50, 10, 10, new Line[] {track1,track2,track3,track4}, 10);
		
		// lives
		lives.add(new PacMan(new float[] { 1, 1, 0 }, liveShift, 16, 8, 8, pacmanStatus.right,0));
		liveShift = updateShift(liveShift);
		lives.add(new PacMan(new float[] { 1, 1, 0 }, liveShift, 16, 8, 8, pacmanStatus.right,0));
		liveShift = updateShift(liveShift);
		lives.add(new PacMan(new float[] { 1, 1, 0 }, liveShift, 16, 8, 8, pacmanStatus.right,0));
		liveShift = updateShift(liveShift);

		// coins//226,135,67
		coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 100, 100, 5, 5)));
		coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 100, 100, 5, 5)));
		coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 150, 50, 5, 5)));
		coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 100, 50, 5, 5)));
		coins.add(new Coin(new Circle(new float[] { 0.98f, 0.71f, 0.26f }, 200, 100, 5, 5)));
		
		//draw map
//		walls.add(new Line(new Point(20, 30), new Point(20, 420), ColorsUtility.BLUE));
//		walls.add(new Line(new Point(20, 420), new Point(580, 420), ColorsUtility.BLUE));
//		walls.add(new Line(new Point(580, 420), new Point(580, 30), ColorsUtility.BLUE));
//		walls.add(new Line(new Point(580, 30), new Point(20, 30), ColorsUtility.BLUE));
		
		walls.add(new Line(new Point(20, 30), new Point(20, 200), ColorsUtility.BLUE));
		walls.add(new Line(new Point(20, 200), new Point(0, 200), ColorsUtility.BLUE));
		walls.add(new Line(new Point(20, 250), new Point(0, 250), ColorsUtility.BLUE));
		walls.add(new Line(new Point(20, 250), new Point(20, 420), ColorsUtility.BLUE));//
		walls.add(new Line(new Point(20, 420), new Point(580, 420), ColorsUtility.BLUE));
		walls.add(new Line(new Point(580, 420), new Point(580, 250), ColorsUtility.BLUE));
		walls.add(new Line(new Point(580, 250), new Point(this.panelSize[0], 250), ColorsUtility.BLUE));//
		walls.add(new Line(new Point(580, 200), new Point(this.panelSize[0], 200), ColorsUtility.BLUE));//
		walls.add(new Line(new Point(580, 200), new Point(580, 30), ColorsUtility.BLUE));
		walls.add(new Line(new Point(580, 30), new Point(20, 30), ColorsUtility.BLUE));
		
		//BOX1
		walls.add(new Line(new Point(60, 70), new Point(60, 150), ColorsUtility.BLUE));
		walls.add(new Line(new Point(60, 70), new Point(200, 70), ColorsUtility.BLUE));
		walls.add(new Line(new Point(200, 70), new Point(200, 150), ColorsUtility.BLUE));
		walls.add(new Line(new Point(200, 150), new Point(60, 150), ColorsUtility.BLUE));
		//BOX2
		walls.add(new Line(new Point(390, 70), new Point(390, 150), ColorsUtility.BLUE));
		walls.add(new Line(new Point(390, 70), new Point(530, 70), ColorsUtility.BLUE));
		walls.add(new Line(new Point(530, 70), new Point(530, 150), ColorsUtility.BLUE));
		walls.add(new Line(new Point(530, 150), new Point(390, 150), ColorsUtility.BLUE));
		//BOX3
		walls.add(new Line(new Point(390, 300), new Point(390, 380), ColorsUtility.BLUE));
		walls.add(new Line(new Point(390, 300), new Point(530, 300), ColorsUtility.BLUE));
		walls.add(new Line(new Point(530, 300), new Point(530, 380), ColorsUtility.BLUE));
		walls.add(new Line(new Point(530, 380), new Point(390, 380), ColorsUtility.BLUE));
		//BOX4
		walls.add(new Line(new Point(60, 300), new Point(60, 380), ColorsUtility.BLUE));
		walls.add(new Line(new Point(60,300), new Point(200, 300), ColorsUtility.BLUE));
		walls.add(new Line(new Point(200, 300), new Point(200, 380), ColorsUtility.BLUE));
		walls.add(new Line(new Point(200, 380), new Point(60, 380), ColorsUtility.BLUE));
		//LEFT CENTER BOX 
		walls.add(new Line(new Point(60, 200), new Point(60, 260), ColorsUtility.BLUE));
		walls.add(new Line(new Point(60,200), new Point(200, 200), ColorsUtility.BLUE));
		walls.add(new Line(new Point(200, 200), new Point(200, 260), ColorsUtility.BLUE));
		walls.add(new Line(new Point(200, 260), new Point(60, 260), ColorsUtility.BLUE));
		//RIGHT CENTER BOX 
		walls.add(new Line(new Point(390, 200), new Point(390, 260), ColorsUtility.BLUE));
		walls.add(new Line(new Point(390, 200), new Point(530, 200), ColorsUtility.BLUE));
		walls.add(new Line(new Point(530, 200), new Point(530, 260), ColorsUtility.BLUE));
		walls.add(new Line(new Point(530, 260), new Point(390, 260), ColorsUtility.BLUE));
		//CENTER WALLS 
		walls.add(new Line(new Point(260, 70), new Point(260, 380), ColorsUtility.BLUE));
		walls.add(new Line(new Point(320, 70), new Point(320, 380), ColorsUtility.BLUE));
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
		ghost.draw(getGl());
		for (Coin coin : coins) {
			coin.getCircle().draw(getGl());
		}
		coinsCollide();

		for (Line line : walls) {
			AlgoUtility.drowLines_STRIP(getGl(), new float[] { 0, 0, 1 }, line, 3);
		}

		for (PacMan live : lives) {
			live.draw(getGl());
		}
		if (coomand) {
			coomand = false;
			if (packman.getStatus() == pacmanStatus.up) {
				packman.up(getGl(), colro);
//				System.out.print("up");
			} else if (packman.getStatus() == pacmanStatus.down) {
				packman.down(getGl(), colro);
//				System.out.print("down");
			} else if (packman.getStatus() == pacmanStatus.left) {
				packman.left(getGl(), colro);
//				System.out.print("left");
			} else if (packman.getStatus() == pacmanStatus.right) {
				packman.right(getGl(), colro);
//				System.out.print("right");
			} else {

			}
//			System.out.print(" : end event\n");
		}
		if(recoveryTime>0) {
			this.recoveryTime--;	
		}
		
		enterPortal();
	}
	
	
	private void enterPortal() {
		if(this.packman.getCenterX()<0) {
			this.packman.setCenterX(this.panelSize[0]);
			System.out.println("Evet : Enter West Portal");
		}else if(this.packman.getCenterX()>this.panelSize[0]) {
			this.packman.setCenterX(0);
			System.out.println("Evet : Enter East Portal");
		}
	}
	
	private void coinsCollide() {
		for (int i = 0; i < coins.size(); i++) {
			if (isCollide(packman.getCenterX(), packman.getCenterY(), packman.getRadius(), coins.get(i).getX(),
					coins.get(i).getY())) {
				coins.remove(i);
			}
		}
	}
	
	private boolean goshtCollide() {
		if(isCollide(packman.getCenterX(), packman.getCenterY(), packman.getRadius()*2, ghost.getCenterX(), ghost.getCenterY())) {
			if(recoveryTime<=0) {
				recoveryTime=50;
				this.lives.removeLast();
				packman.setNoDirection();
				packman.draw(getGl());
				if(this.lives.isEmpty()) {
					getAnimator().stop();
				}
			}
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
