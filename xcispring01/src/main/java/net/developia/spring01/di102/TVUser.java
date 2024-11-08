package net.developia.spring01.di102;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		System.out.println("----------1--------");
		GenericXmlApplicationContext context = new GenericXmlApplicationContext(TVUser.class, "beaninit.xml");
		System.out.println("----------2--------");
		TV tv = (TV) context.getBean("samsungtv"); // new 대신 이미 만들어져있는 tv를 가져옴.
		// beaninit에 정의된 name으로 가져옴.
		// 무슨 object인지 모르니까 최상위 클래스인 Object 클래스로 가져옴.
		// 그래서 TV로 다운캐스팅
		tv.powerOn();
		tv.channelUp();
		tv.channelUp();
		tv.soundUp();
		tv.soundUp();
		tv.soundDown();
		tv.powerOff();
	}
}