package GUI;


import javax.swing.JFrame;

import javafx.scene.media.AudioClip;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;


public class StartWindow {

	private JFrame frmStart;
	private AudioClip audioClip;
	
	public StartWindow() {
		initialize();
		audioClip = new AudioClip(new File("resource\\audio\\intro.mp3").toURI().toString());
		audioClip.setCycleCount(100);
		audioClip.play();
	}

	private void initialize() {
		frmStart = new JFrame();
		frmStart.setTitle("Start");
		frmStart.setResizable(false);
		frmStart.setBounds(100, 100, 632, 314);
		frmStart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStart.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameWindow window = new GameWindow();
				window.getFrame().setVisible(true);
				frmStart.setVisible(false);
				audioClip.stop();
			}
		});
		btnNewButton.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		btnNewButton.setBounds(127, 70, 331, 108);
		frmStart.getContentPane().add(btnNewButton);
	}

	public JFrame getFrame() {
		return this.frmStart;
	}
}
