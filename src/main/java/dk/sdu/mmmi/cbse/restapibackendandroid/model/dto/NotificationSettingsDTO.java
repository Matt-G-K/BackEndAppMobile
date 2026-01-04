package dk.sdu.mmmi.cbse.restapibackendandroid.model.dto;


import dk.sdu.mmmi.cbse.restapibackendandroid.model.types.NotificationType;

public class NotificationSettingsDTO {
    NotificationType type;
    boolean enabled;

    public NotificationSettingsDTO(NotificationType type, boolean enabled) {
        this.type = type;
        this.enabled = enabled;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
