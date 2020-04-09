package com.bokecc;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.bokecc.mapper")
@ServletComponentScan(basePackages = {"com.bokecc"})
public class Application implements CommandLineRunner
{
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Autowired
    public RestTemplate restTemplate;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info(":::#####################################################################");

        log.info(":::Started successfully!");

        log.info(":::#####################################################################");
    }
}
