package dk.sdu.mmmi.cbse.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;


@Service
public class NotificationService {
    private final DeviceTokenService deviceTokenService;

    public NotificationService(DeviceTokenService deviceTokenService) {
        this.deviceTokenService = deviceTokenService;
    }

    public void sendGroupInvitationNotification(String userId, String groupName) {
        // Retrieve device tokens for the user
        var tokens = deviceTokenService.getTokensUser(userId);

        // Sending notification to each token
        for (String token : tokens) {
            System.out.println("Sending group invitation notification to token: " + token +
                               " for group: " + groupName);
            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(
                        Notification.builder()
                            .setTitle("Group Invitation")
                            .setBody("You have been invited to the group: " + groupName)
                            .build()
                    )
                    .putData("type", "GROUP_INVITATION")
                    .putData("groupName", groupName)
                    .build();
                    
                    FirebaseMessaging.getInstance().sendAsync(message);
        }
    }
}
