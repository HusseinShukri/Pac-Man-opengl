package GUI;

import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;

import JOGL.OpenGLCanvas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GameWindow {

	private JFrame frmPacMan;
	private JPanel joglPanel;
	private JLabel scoreLabel;

	public JFrame getFrame() {
		return frmPacMan;
	}

	public GameWindow() {
		initialize();
	}

	private void initialize() {
		frmPacMan = new JFrame();
		frmPacMan.setTitle("Pac Man");
		frmPacMan.setBounds(100, 100, 631, 562);
		frmPacMan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPacMan.getContentPane().setLayout(null);

		joglPanel = new JPanel();
		joglPanel.setBounds(10, 10, 600, 450);
		frmPacMan.getContentPane().add(joglPanel);
		
		scoreLabel = new JLabel("0");
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Snap ITC", Font.PLAIN, 20));
		scoreLabel.setBounds(187, 470, 242, 24);
		frmPacMan.getContentPane().add(scoreLabel);

		try {
			joglSetup(joglPanel.getWidth(), joglPanel.getHeight());
		} catch (GLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void joglSetup(int w, int h) throws GLException, Exception {
		test= new OpenGLCanvas(this.getFrame(),new GLCapabilities(GLProfile.get(GLProfile.GL2)), w, h,scoreLabel);
		joglPanel.add(test.getCanvas());
	}

	private OpenGLCanvas test;
}
