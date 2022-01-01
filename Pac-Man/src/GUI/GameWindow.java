package GUI;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;

import JOGL.OpenGLCanvas;

import javax.swing.JPanel;

public class GameWindow {

	private JFrame frmPacMan;
	private JPanel joglPanel;

	public JFrame getFrame() {
		return frmPacMan;
	}

	public GameWindow() {
		initialize();
	}

	private void initialize() {
		frmPacMan = new JFrame();
		frmPacMan.setTitle("Pac Man");
		frmPacMan.setBounds(100, 100, 917, 562);
		frmPacMan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPacMan.getContentPane().setLayout(null);

		joglPanel = new JPanel();
		joglPanel.setBounds(10, 10, 600, 450);
		frmPacMan.getContentPane().add(joglPanel);

		try {
			joglSetup(joglPanel.getWidth(), joglPanel.getHeight());
		} catch (GLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void joglSetup(int w, int h) throws GLException, Exception {
		System.out.println("start ");
		test= new OpenGLCanvas(new GLCapabilities(GLProfile.get(GLProfile.GL2)), w, h);
		joglPanel.add(test.getCanvas());
	}

	private OpenGLCanvas test;

}
