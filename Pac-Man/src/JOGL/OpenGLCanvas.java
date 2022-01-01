package JOGL;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;


public class OpenGLCanvas {
	
	private GLCanvas glCanvas;
	private CanvasLoopListener glLisenner;

	/**
	 * 
	 * @param capabilities
	 * @param width
	 * @param height
	 * @return 
	 * @throws Exception
	 */
	public  OpenGLCanvas(GLCapabilities capabilities, int width, int height) throws Exception {
		this.glCanvas = new GLCanvas(capabilities);
		initCanvas(glCanvas, width, height);
	}

	private void initCanvas(GLCanvas glCanvas,int width, int height) throws Exception {
		glLisenner = new CanvasLoopListener();
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
