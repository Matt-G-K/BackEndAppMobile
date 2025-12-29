package dk.sdu.mmmi.cbse.restapibackendandroid.service;

import org.springframework.stereotype.Service;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;



@Service
public class NotificationService {
    private final DeviceTokenService deviceTokenService;
    private final FcmService fcmService;

    public NotificationService(DeviceTokenService deviceTokenService, FcmService fcmService) {
        this.deviceTokenService = deviceTokenService;
        this.fcmService = fcmService;
    }


    // Method to send a group invitation notification
    public void sendGroupInvitationNotification(String userId, String groupName) {
        var tokens = deviceTokenService.getTokensUser(userId);

        for(String token: tokens){
            System.out.println("Sending group invitation notification to token: " + token +
                                   " for group: " + groupName);
            fcmService.sendPushNotification(
                userId,
                token,
                "Added To Group",
                "You have been added to the group: " + groupName,
                NotificationType.ADDED_TO_GROUP
            );
        }
    }

    // Method to send a group payment reminder notification
    public void sendGroupPing(String userId, String groupName) {
        var tokens = deviceTokenService.getTokensUser(userId);

        for(String token: tokens){
            System.out.println("Sending group ping notification to token: " + token);
            fcmService.sendPushNotification(
                userId,
                token,
                "Group Payment Reminder",
                "Don't forget to settle your payments in the group: " + groupName,
                NotificationType.GROUP_PING
            );
        }
    }

}
