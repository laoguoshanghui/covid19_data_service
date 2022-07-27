package com.hqyj.covid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 运行操作：
 * 1、url:localhost:80
 * 2、开启nginx前端服务器
 * 3、nginx -s reload  ：修改配置后重新加载生效
 */
@SpringBootApplication
@EnableTransactionManagement


public class Covid19Application {

	public static void main(String[] args) {
		SpringApplication.run(Covid19Application.class, args);
	}

}
