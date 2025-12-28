package dk.sdu.mmmi.cbse.firebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;


// Firebase configuration class to initialize FirebaseApp, using service account credentials from classpath
@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() throws IOException {
        System.out.println("Initializing Firebase...");

        ClassPathResource resource = new ClassPathResource("firebase/splitwiz-51e10-firebase-adminsdk-fbsvc-7f923ac80d.json");

         if (!resource.exists()) {
            throw new IllegalStateException(
                    "Firebase service account file not found on classpath at firebase/splitwiz-51e10-firebase-adminsdk-fbsvc-7f923ac80d.json");
        }

        try (InputStream serviceAccount = resource.getInputStream()) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("FirebaseApp [DEFAULT] initialized");
            } else {
                System.out.println("FirebaseApp already initialized, reusing existing instance");
            }
        }
    }
}
