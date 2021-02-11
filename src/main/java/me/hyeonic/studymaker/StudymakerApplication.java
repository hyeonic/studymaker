package me.hyeonic.studymaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudymakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudymakerApplication.class, args);
    }

}
