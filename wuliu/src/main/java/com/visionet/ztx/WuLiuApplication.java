package com.visionet.ztx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.visionet.ztx")
public class WuLiuApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WuLiuApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WuLiuApplication.class);
	}
}
