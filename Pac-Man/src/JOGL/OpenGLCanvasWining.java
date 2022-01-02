package JOGL;


import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;

public class OpenGLCanvasWining {
	
	private GLCanvas glCanvas;
	private WinLoop glLisenner;

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
	public  OpenGLCanvasWining (GLCapabilities capabilities, int width, int height) throws Exception {
		this.glCanvas = new GLCanvas(capabilities);
		initCanvas(glCanvas, width, height);
	}

	private void initCanvas(GLCanvas glCanvas,int width, int height) throws Exception {
		glLisenner = new WinLoop();
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
