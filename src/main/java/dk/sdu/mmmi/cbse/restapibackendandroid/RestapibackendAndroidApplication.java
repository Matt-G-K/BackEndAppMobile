package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "dk.sdu.mmmi.cbse.restapibackendandroid",
    "dk.sdu.mmmi.cbse.service",
    "dk.sdu.mmmi.cbse.firebase"
})
public class RestapibackendAndroidApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapibackendAndroidApplication.class, args);
    }

}
