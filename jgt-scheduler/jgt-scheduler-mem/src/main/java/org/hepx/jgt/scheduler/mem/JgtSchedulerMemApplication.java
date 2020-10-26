package org.hepx.jgt.scheduler.mem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JgtSchedulerMemApplication {

    public static void main(String[] args) {
        SpringApplication.run(JgtSchedulerMemApplication.class, args);
    }

}
