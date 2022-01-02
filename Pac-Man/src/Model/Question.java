package Model;

import java.util.LinkedList;

public class Question {
	private String question;
	private LinkedList<String> ansersList;
	private int correctAnser;

	public Question(int correctAnser, String question, String a1, String a2, String a3) {
		super();
		this.ansersList = new LinkedList<String>();
		this.ansersList.add(a1);
		this.ansersList.add(a2);
		this.ansersList.add(a3);
		this.question = question;
		this.correctAnser = correctAnser;
	}


	public String getQuestion() {
		return question;
	}

	public LinkedList<String> getAnsersList() {
		return ansersList;
	}

	public int getCorrectAnser() {
		return correctAnser;
	}

}
