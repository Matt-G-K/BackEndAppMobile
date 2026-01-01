package dk.sdu.mmmi.cbse.restapibackendandroid.service;

import org.springframework.stereotype.Service;



import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;




@Service
public class FcmService {

    NotificationSettingService notificationSettingService;

    public FcmService(NotificationSettingService notificationSettingService) {
        this.notificationSettingService = notificationSettingService;
    }


    // General method to send push notification
    public void sendPushNotification(String userId, String token, String title, String body, NotificationType type) {
        Message message = Message.builder()
                .setToken(token)
                .putData("title", title)
                .putData("body", body)
                .build();

        try {
            if (notificationSettingService.isNotificationEnabled(userId, type)) {
                FirebaseMessaging.getInstance().send(message);
                System.out.println("FCM notification sent!");
            }else {
                System.out.println("FCM notification of type " + type + " is disabled for user: " + userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
