package dk.sdu.mmmi.cbse.restapibackendandroid.model;

public class DeviceToken {
    private String userId;
    private String token;

    public DeviceToken() {
    }

    public DeviceToken(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
