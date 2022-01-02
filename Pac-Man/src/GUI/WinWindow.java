package GUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;

public class WinWindow {

	private JFrame frmWin;

	public JFrame getFrmWin() {
		return frmWin;
	}
	
	public static void main(String[] args) {
		WinWindow window  =new WinWindow();
		window.getFrmWin().setVisible(true);
	}

	public WinWindow() {
		initialize();

	}

	private void initialize() {
		frmWin = new JFrame();
		frmWin.setTitle("Win");
		frmWin.setBounds(100, 100, 854, 532);
		frmWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWin.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setBounds(10, 10, 820, 475);
		frmWin.getContentPane().add(lblNewLabel);

		ImageIcon img1 = new ImageIcon("E:\\Projects\\Pac-Man-opengl\\Pac-Man\\resource\\Win.jpg");
		java.awt.Image img2 = img1.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), 1);
		ImageIcon img3 = new ImageIcon(img2);
		lblNewLabel.setIcon(img3);

	}
}
