package dk.sdu.mmmi.cbse.restapibackendandroid.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.NotificationSetting;
import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;
import dk.sdu.mmmi.cbse.restapibackendandroid.service.NotificationSettingService;
import dk.sdu.mmmi.cbse.restapibackendandroid.model.dto.NotificationSettingsDTO;

@RestController
@CrossOrigin(origins = "*")
public class NotificationSettingController {

    private final NotificationSettingService notificationSettingService;

    public NotificationSettingController(NotificationSettingService notificationSettingService) {
        this.notificationSettingService = notificationSettingService;
    }

    // Get notification settings for a user
    @GetMapping("/api/notificationsettings/{userId}")
    public NotificationSetting getNotificationSetting(@PathVariable String userId) {
        return notificationSettingService.getNotificationSetting(userId);
    }

    // Update notification setting for a user
    @PutMapping("/api/notificationsettings/update/{userId}/{type}/{enabled}")
    public String updateNotificationSetting(@PathVariable String userId, @RequestBody NotificationSettingsDTO dto) {
        notificationSettingService.updateNotificationSetting(userId, dto.getType(), dto.isEnabled());
        return "Notification setting updated successfully";
    }
}
