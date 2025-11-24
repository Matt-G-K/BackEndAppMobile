package dk.sdu.mmmi.cbse.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;


@Service
public class FcmService {
    private static final String FcmURL = "https://fcm.googleapis.com/fcm/send";

    private static final String FcmServerKey = "BM5pAOJLGMPRK1g4zjsXbY1bARJF4pPJQYvY-ALYH8FzzS_s1EagQ81CbMFCjqWGqCQbqIe8eFUpMjG_LMG-u5o";




    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();


    // Temporary test
    public void sendGroupInviteNotification(String deviceToken, int groupId, String username) {
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("to", deviceToken);

            Map<String, String> notification = new HashMap<>();
            notification.put("title", "New group invitation");
            notification.put("body", username + ", you were added to group " + groupId);
            body.put("notification", notification);

            Map<String, String> data = new HashMap<>();
            data.put("groupId", String.valueOf(groupId));
            data.put("username", username);
            body.put("data", data);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "key=" + FcmServerKey);

            HttpEntity<String> request =
                    new HttpEntity<>(objectMapper.writeValueAsString(body), headers);

            ResponseEntity<String> response =
                    restTemplate.postForEntity(FcmURL, request, String.class);

            System.out.println("FCM response: " + response.getStatusCode() + " - " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error sending FCM: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void sendPushNotification(String token, String title, String body) {
        Message message = Message.builder()
                .setToken(token)
                .putData("title", title)
                .putData("body", body)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
            System.out.println("FCM notification sent!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
