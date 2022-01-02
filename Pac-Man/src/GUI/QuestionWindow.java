package GUI;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import Dto.QuestionDto;
import JOGL.CanvasLoopListener;
import Model.Question;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuestionWindow {

	private JFrame frmQuestion;
	private JLabel qLabel;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;

	public JFrame getFrame() {
		return frmQuestion;
	}

	public QuestionWindow(CanvasLoopListener loopListener,QuestionDto dto, Question question) {
		initialize(loopListener,dto, question.getCorrectAnser());
		this.qLabel.setText(question.getQuestion());
		this.button_1.setText(question.getAnsersList().get(0));
		this.button_2.setText(question.getAnsersList().get(1));
		this.button_3.setText(question.getAnsersList().get(2));

	}

	private void initialize(CanvasLoopListener loopListener,QuestionDto dto, int correctAnser) {
		frmQuestion = new JFrame();
		frmQuestion.getContentPane().setBackground(Color.ORANGE);
		frmQuestion.setTitle("Question Time :\")");
		frmQuestion.setBounds(100, 100, 907, 511);
		frmQuestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuestion.getContentPane().setLayout(null);

		qLabel = new JLabel("");
		qLabel.setHorizontalAlignment(SwingConstants.CENTER);
		qLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		qLabel.setBounds(10, 26, 873, 136);
		frmQuestion.getContentPane().add(qLabel);

		button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (correctAnser == 1) {
					System.out.println("أحسنت");
					dto.isAnsweredCorrect = true;
				}
				dto.isAnswered = true;
				getFrame().setVisible(false);
				loopListener.resum();
			}
		});
		button_1.setBounds(10, 188, 873, 69);
		frmQuestion.getContentPane().add(button_1);

		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (correctAnser == 2) {
					System.out.println("أحسنت");
					dto.isAnsweredCorrect = true;
				}
				dto.isAnswered = true;
				getFrame().setVisible(false);
				loopListener.resum();
			}
		});
		button_2.setBounds(10, 283, 873, 69);
		frmQuestion.getContentPane().add(button_2);

		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (correctAnser == 3) {
					System.out.println("أحسنت");
					dto.isAnsweredCorrect = true;
				}
				dto.isAnswered = true;
				getFrame().setVisible(false);
				loopListener.resum();
			}
		});
		button_3.setBounds(10, 378, 873, 69);
		frmQuestion.getContentPane().add(button_3);
	}

}
