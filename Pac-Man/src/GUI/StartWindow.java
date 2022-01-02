package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class StartWindow {

	private JFrame frmStart;

//	private Clip clip;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					window.frmStart.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
			}
		});
		btnNewButton.setFont(new Font("Snap ITC", Font.PLAIN, 30));
		btnNewButton.setBounds(127, 70, 331, 108);
		frmStart.getContentPane().add(btnNewButton);
	}
}
