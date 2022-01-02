package Utilitiy;

import java.io.File;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	
	public static void playStart() {
		AudioClip audioClip = new AudioClip(new File("resource\\audio\\intro.mp3").toURI().toString());
		audioClip.play();
	}

	public static void playDeath() {
		AudioClip audioClip = new AudioClip(
				new File("resource\\audio\\pacman_death.mp3").toURI()
						.toString());
		audioClip.play();
	}
	
	public static void playEatCoin() {
		AudioClip audioClip = new AudioClip(
				new File("resource\\audio\\eat_dot.mp3").toURI()
						.toString());
		audioClip.play();
	}
	
	public static void playLiveLost() {
		AudioClip audioClip = new AudioClip(
				new File("resource\\audio\\eat_ghost.mp3").toURI()
						.toString());
		audioClip.play();
	}
	
	public static void playWrong() {
		AudioClip audioClip = new AudioClip(
				new File("resource\\audio\\Wrong.mp3").toURI()
						.toString());
		audioClip.play();
	}
	
	public static void playCorrect() {
		AudioClip audioClip = new AudioClip(
				new File("resource\\audio\\correct.mp3").toURI()
						.toString());
		audioClip.play();
	}
	
	
	public static void playWining() {
		AudioClip audioClip = new AudioClip(
				new File("resource\\audio\\winning.mp3").toURI()
						.toString());
		audioClip.play();
	}
	
	public static void playWininglOOP() {
		AudioClip audioClip = new AudioClip(
				new File("resource\\audio\\winning.mp3").toURI()
						.toString());
		audioClip.setCycleCount(100);
		audioClip.play();
	}
}
