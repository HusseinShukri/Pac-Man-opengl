package Model;

import java.util.LinkedList;

public class QuestionsList {
	LinkedList<Question> questions;
	int currentQuestion = 0;

	public QuestionsList() {
		super();
		this.questions = new LinkedList<Question>();
		this.questions.add(new Question(1, "ما هي عاصمه فلسطين؟ ", "القدس", "الخليل", "رام الله"));
		this.questions.add(new Question(2, "ما اسم المساق", "جافا", "الرسم بالحاسوب", "قاوعد البيانات"));
		this.questions.add(new Question(3, "80 + 50 / 5", "26", "66", "90"));
		this.questions.add(new Question(1, "متى حدثت النكبة", "1948", "1946", "1927"));
		this.questions.add(new Question(2, "عدد عيون النحل", "3", "5", "2"));
		this.questions.add(new Question(2, "متى حدثت النكسة", "1948", "1967", "1927"));
		
		
	}

	public Question getQuestion() {

		Question q = questions.get(currentQuestion);
		System.out.println("index "+currentQuestion);
		currentQuestion++;
		if (currentQuestion == questions.size()) {
			currentQuestion = 0;
		}
		return q;
	}
}
