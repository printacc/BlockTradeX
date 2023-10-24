package com.muses.er.normal.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEvent implements ApplicationListener<ContextRefreshedEvent> {
	
	private final static  Logger logger  =  LoggerFactory.getLogger(ApplicationEvent.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		logger.info("===============================================");
		logger.info("===============交易机器人初始化===============");
		logger.info("===============================================");
	}

}
