package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;

import JOGL.OpenGLCanvasWining;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class WinWindow2 {

	private JFrame frmWinner;
	private JPanel JOGLPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinWindow2 window = new WinWindow2();
					window.frmWinner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WinWindow2() {
		initialize();
	}

	private void initialize() {
		frmWinner = new JFrame();
		frmWinner.setTitle(" WINNER");
		frmWinner.setBounds(100, 100, 630, 513);
		frmWinner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWinner.getContentPane().setLayout(null);
		
		JOGLPanel = new JPanel();
		JOGLPanel.setBounds(10, 10, 600, 400);
		frmWinner.getContentPane().add(JOGLPanel);
		
		JLabel lblNewLabel = new JLabel("\u0627\u0644\u0641 \u0627\u0644\u0641 \u0645\u0628\u0631\u0648\u0643 ");
		lblNewLabel.setFont(new Font("Arabic Typesetting", Font.BOLD, 53));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 409, 600, 57);
		frmWinner.getContentPane().add(lblNewLabel);
		try {
			joglSetup(JOGLPanel.getWidth(), JOGLPanel.getHeight());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void joglSetup(int w, int h) throws GLException, Exception {
		OpenGLCanvasWining test= new OpenGLCanvasWining(new GLCapabilities(GLProfile.get(GLProfile.GL2)), w, h);
		JOGLPanel.add(test.getCanvas());
	}

	public JFrame getFrmWinner() {
		return frmWinner;
	}

	public void setFrmWinner(JFrame frmWinner) {
		this.frmWinner = frmWinner;
	}
	
	
}
