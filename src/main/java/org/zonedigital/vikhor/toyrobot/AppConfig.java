package org.zonedigital.vikhor.toyrobot;

import java.io.PrintStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean(name = "reportCommandStream")
	public PrintStream reportCommandStream() {
		return System.out;
	}
	
}
