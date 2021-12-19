package com.sge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @Description
 * @createTime 2021/12/02
 */
@SpringBootApplication
@EnableJpaRepositories
@EntityScan
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(Application.class, args);
        PlatformTransactionManager bean = context.getBean(PlatformTransactionManager.class);
        System.out.println(bean);//自动注入了JpaTransactionManager
    }
}
