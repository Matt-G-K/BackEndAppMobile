package dk.sdu.mmmi.cbse.restapibackendandroid.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


import dk.sdu.mmmi.cbse.restapibackendandroid.model.NotificationSetting;
import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;
import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.NotificationSettingStore;

@Service
public class NotificationSettingService {
    
    private final NotificationSettingStore store;

    public NotificationSettingService(NotificationSettingStore store) {
        this.store = store;
    }

    // Create default notification settings for a new user
    public void createDefaultSettings(String userId) {
        store.getOrCreate(userId);
        System.out.println("Created default notification settings for userId: " + userId);
    }

    // Check if a specific notification type is enabled for a user
    public boolean isNotificationEnabled(String userId, NotificationType type) {
        return store
                .getOrCreate(userId)
                .isEnabled(type);
    }

    // Update notification setting for a user
    public void updateNotificationSetting(String userId, NotificationType type, boolean enabled) {
        store
                .getOrCreate(userId)
                .setEnabled(type, enabled);
    }

    // Retrieve notification settings for a user
    public NotificationSetting getNotificationSetting(String userId) {
        return store.getOrCreate(userId);
    }

}
