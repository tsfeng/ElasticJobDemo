package com.tsfeng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author tsfeng
 * @date 2020/4/13 10:38 上午
 */
@Slf4j
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
        log.info("JobApplication start success");
    }
}
