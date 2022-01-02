package JOGL;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;


public class OpenGLCanvas {
	
	private GLCanvas glCanvas;
	private CanvasLoopListener glLisenner;

	/**
	 * 
	 * @param jFrame 
	 * @param capabilities
	 * @param width
	 * @param height
	 * @param scoreLabel 
	 * @return 
	 * @throws Exception
	 */
	public  OpenGLCanvas(JFrame jFrame, GLCapabilities capabilities, int width, int height, JLabel scoreLabel) throws Exception {
		this.glCanvas = new GLCanvas(capabilities);
		initCanvas(jFrame,glCanvas, width, height,scoreLabel);
	}

	private void initCanvas(JFrame jFrame, GLCanvas glCanvas,int width, int height, JLabel scoreLabel) throws Exception {
		glLisenner = new CanvasLoopListener(scoreLabel,jFrame);
		glCanvas.addGLEventListener(glLisenner);
		glCanvas.addMouseListener(glLisenner);
		glCanvas.addMouseMotionListener(glLisenner);
		glCanvas.addKeyListener(glLisenner);
		glCanvas.addMouseWheelListener(glLisenner);
		glCanvas.setSize(width, height);
		
	}

	public GLCanvas getCanvas() {
		return this.glCanvas;
	}


}
