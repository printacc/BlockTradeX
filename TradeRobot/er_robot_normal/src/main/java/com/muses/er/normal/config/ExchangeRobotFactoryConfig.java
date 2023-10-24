package com.muses.er.normal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.muses.er.normal.robot.ExchangeRobotFactory;

@Configuration
public class ExchangeRobotFactoryConfig {
	@Bean
	public ExchangeRobotFactory getFactory() {
		ExchangeRobotFactory factory = new ExchangeRobotFactory();
		return factory;
	}
}
