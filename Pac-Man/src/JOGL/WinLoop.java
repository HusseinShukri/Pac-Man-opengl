package JOGL;

import java.util.LinkedList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.FPSAnimator;

import Model.PacMan;
import Model.pacmanStatus;
import Utilitiy.AudioUtility;

public class WinLoop extends ClassJOGL {
	private int[] panelSize = new int[] { 0, 0 };
	private LinkedList<PacMan> lives = new LinkedList<PacMan>();
	private int x;
	private int y;
	private pacmanStatus statue=pacmanStatus.noDirection;
	private boolean sFlage = false;

	@Override
	public void init(GLAutoDrawable drawable) {
		super.init(drawable);
		setGl(drawable.getGL().getGL2());
		panelSize[0] = drawable.getSurfaceWidth();
		panelSize[1] = drawable.getSurfaceHeight();
		getGl().glClearColor(getBackgroundColor()[0], getBackgroundColor()[1], getBackgroundColor()[2], 0f);
		getGl().glClear(GL.GL_COLOR_BUFFER_BIT);
		
		this.x=10;
		this.y=panelSize[1]-10;
		
		this.setAnimator(new FPSAnimator(drawable, 10, true));
		this.getAnimator().start();
		
		AudioUtility.playWininglOOP();
		
	}
	
	private void updateX() {
		if(x>580) {
			x=10;
			updatetY();
		}else {
			x+=20;
		}
	}
	private void updatetY() {
		y-=20;
		if(sFlage) {
			sFlage=false;
			statue=pacmanStatus.left;
		}else {
			sFlage=true;
			statue=pacmanStatus.right;
		}
		
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
		getGl().glViewport(50, 50, panelSize[0], panelSize[1] ); // (x,y,width,height)
		if (lives.size() < 2400) {
			lives.add(new PacMan(new float[] { 1, 1, 0 }, x, y, 10, 10, statue, 0));
			updateX();
		}

		for (PacMan live : lives) {
			live.draw(getGl());
		}
	}

}
