// package dk.sdu.mmmi.cbse.restapibackendandroid.controller;

// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.google.firebase.messaging.FirebaseMessaging;
// import com.google.firebase.messaging.Message;

// import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;
// import dk.sdu.mmmi.cbse.restapibackendandroid.service.DeviceTokenService;
// import dk.sdu.mmmi.cbse.restapibackendandroid.service.FcmService;

// @RestController
// @RequestMapping("/api/test")
// public class TestNotificationController {

//     private final FcmService fcmService;
//     private final DeviceTokenService deviceTokenService;

//     public TestNotificationController(FcmService fcmService, DeviceTokenService deviceTokenService) {
//         this.fcmService = fcmService;
//         this.deviceTokenService = deviceTokenService;
//     }

//     @GetMapping("/send")
//     public String sendTestNotification() {
//         String testToken = "dLMuLsNwTSqQ9ywYUtuiO7:APA91bFbglTHK4C3yOXVdLF75KRFpKJFHijIcthUAeCAgDUkv2gCEO_cd7sI7m-IFyaLWRFJmktJlJC15LwV3BEDesp-JYcrAGQrSls82QiXoD1NJU6Qc6A";

//         fcmService.sendPushNotification(
//                 "1",
//                 testToken,
//                 "Test Notification",
//                 "If you see this, FCM works!",
//                 NotificationType.TEST
//         );

//         return "Notification sent!";
//     }


//     @GetMapping("/api/test/send")
//     public String testSend() throws Exception {

//         String testToken = "dLMuLsNwTSqQ9ywYUtuiO7:APA91bFbglTHK4C3yOXVdLF75KRFpKJFHijIcthUAeCAgDUkv2gCEO_cd7sI7m-IFyaLWRFJmktJlJC15LwV3BEDesp-JYcrAGQrSls82QiXoD1NJU6Qc6A";

//         Message message = Message.builder()
//                 .putData("title", "Test Notification")
//                 .putData("body", "If you see this, FCM is working!")
//                 .setToken(testToken)
//                 .build();

//         String response = FirebaseMessaging.getInstance().send(message);

//         return "Message sent: " + response;
//     }


//     // Send test notification to specific user device token
//     @GetMapping("/send/{userId}")
//     public String sendTestNotificationToToken(@PathVariable String userId) {
//         var tokens = deviceTokenService.getTokensUser(userId);
//         System.out.println("Found " + tokens.toString() + " tokens for user: " + userId);
//         for (String token : tokens) {
//             fcmService.sendPushNotification(
//                     token,
//                     "Test Notification to User",
//                     "If you see this, FCM to user works!",
//                     NotificationType.TEST
//             );
//         }
//         return "Notifications sent to user: " + userId;
//     }
// }