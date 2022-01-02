package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoseWindow {

	private JFrame frmLose;

	public JFrame getFrmLose() {
		return frmLose;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoseWindow window = new LoseWindow();
					window.frmLose.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoseWindow() {
		initialize();
	}

	private void initialize() {
		frmLose = new JFrame();
		frmLose.setTitle("Lose");
		frmLose.setBounds(100, 100, 854, 532);
		frmLose.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLose.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 10, 820, 475);
		frmLose.getContentPane().add(lblNewLabel);

		ImageIcon img1 = new ImageIcon("E:\\Projects\\Pac-Man-opengl\\Pac-Man\\resource\\GameOver.jpg");
		java.awt.Image img2 = img1.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), 1);
		ImageIcon img3 = new ImageIcon(img2);
		lblNewLabel.setIcon(img3);
	}

}
