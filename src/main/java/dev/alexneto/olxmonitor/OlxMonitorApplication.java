package dev.alexneto.olxmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class OlxMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OlxMonitorApplication.class, args);
    }

}
