package dk.sdu.mmmi.cbse.restapibackendandroid;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class DeviceTokenStore {

     // userId -> deviceToken
    private final Map<String, String> tokens = new ConcurrentHashMap<>();

    public void saveToken(String userId, String token) {
        tokens.put(userId, token);
        System.out.println("Saved token for userId: " + userId + " -> " + token);
    }

    public String getTokenForUser(String userId) {
        return tokens.get(userId);
    }

    public Map<String, String> getAllTokens() {
        return new HashMap<>(tokens);
    }
}
