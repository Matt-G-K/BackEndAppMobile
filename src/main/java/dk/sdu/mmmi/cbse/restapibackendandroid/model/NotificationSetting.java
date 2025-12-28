package dk.sdu.mmmi.cbse.restapibackendandroid.model;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;

public class NotificationSetting {
    private String userId;
    private Map<NotificationType, Boolean> settings = new EnumMap<>(NotificationType.class);

    public NotificationSetting(@PathVariable String userId) {
        this.userId = userId;
        for (NotificationType type : NotificationType.values()) {
            settings.put(type, true); // Default all notifications to enabled
        }
    }

    public boolean isEnabled(NotificationType type) {
        return settings.getOrDefault(type, false);
    }

    public void setEnabled(NotificationType type, boolean enabled) {
        settings.put(type, enabled);
    }

    public String getUserId() {
        return userId;
    }

    public String setUserId(String userId) {
        this.userId = userId;
        return userId;
    }

    public Map<NotificationType, Boolean> getSettings() {
        return settings;
    }
}
