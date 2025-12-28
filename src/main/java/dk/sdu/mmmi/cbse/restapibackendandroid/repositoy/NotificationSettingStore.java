package dk.sdu.mmmi.cbse.restapibackendandroid.repositoy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.NotificationSetting;
import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;

@Component
public class NotificationSettingStore {
    private final Map<String, NotificationSetting> store = new HashMap<>();

    // Retrieve existing settings or create default if not present
    public NotificationSetting getOrCreate(String userId) {
        return store.computeIfAbsent(userId, NotificationSetting::new);
    }

    // Update settings for a user
    public void update(String userId, NotificationType type, boolean enabled) {
        getOrCreate(userId).setEnabled(type, enabled);
    }

    // Check if a notification type is enabled for a user
    public boolean isEnabled(String userId, NotificationType type) {
        return getOrCreate(userId).isEnabled(type);
    }
}
