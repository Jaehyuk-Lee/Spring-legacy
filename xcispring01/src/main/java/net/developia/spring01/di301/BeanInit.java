package net.developia.spring01.di301;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInit {
	@Bean
	public TV tv() {
		SamsungTV tv = new SamsungTV();
		tv.setSpeaker(speaker());
		return tv;
	}
	
	@Bean
	public Speaker speaker() {
		return new HarmanSpeaker();
	}
}
