package io.tdd.lectureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LectureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LectureServiceApplication.class, args);
    }

}
