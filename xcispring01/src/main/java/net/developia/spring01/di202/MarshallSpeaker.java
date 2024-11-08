package net.developia.spring01.di202;

// @Component("mspeaker")
public class MarshallSpeaker implements Speaker{

	@Override
	public void soundUp() {
		System.out.println("MarshallSpeaker: 소리를 키웁니다.");
	}

	@Override
	public void soundDown() {
		System.out.println("MarshallSpeaker: 소리를 줄입니다.");
	}

}
