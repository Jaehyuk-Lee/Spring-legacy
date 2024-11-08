package net.developia.spring01.di302;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanInit {
	@Bean
	public TV tv() {
		return new SamsungTV(speaker());
	}
	
	@Bean
	public Speaker speaker() {
		return new HarmanSpeaker();
	}
}
