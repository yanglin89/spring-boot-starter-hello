package com.run.spring_boot_starter_hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author user
 * 根据HelloServiceProperties提供的参数，并通过@ConditionalOnClass判断HelloService
 * 这个类在类路径中是否存在，其当容器中没有这个Bean的情况下自动配置这个bean
 */
@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(prefix="hello",value="enabled",matchIfMissing=true)
public class HelloServiceAutoConfiguration {

	@Autowired
	private HelloServiceProperties helloServiceProperties;
	
	@Bean
	@ConditionalOnMissingBean(HelloService.class)
	public HelloService helloService() {
		HelloService helloService = new HelloService();
		helloService.setMsg(helloServiceProperties.getMsg());
		return helloService;
	}
	
}
