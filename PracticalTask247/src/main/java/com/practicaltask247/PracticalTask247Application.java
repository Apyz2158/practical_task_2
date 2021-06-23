package com.practicaltask247;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Practical Task 247", version = "0.1", description = "API documentation of PRACTICAL TASK 247 project."))
public class PracticalTask247Application {

    public static void main(String[] args) {
        SpringApplication.run(PracticalTask247Application.class, args);
    }

}
