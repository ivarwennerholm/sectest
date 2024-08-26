package org.example.secvg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class SecvgApplication {

    public static void main(String[] args) {

        if (args.length == 0) {
            SpringApplication app = new SpringApplication(SecvgApplication.class);
            app.run(args);
        } else if (Objects.equals(args[0], "createhashes")) {
            SpringApplication app = new SpringApplication(CreateHashes.class);
            app.setWebApplicationType(WebApplicationType.NONE);
            app.run(args);
        }
    }

}
