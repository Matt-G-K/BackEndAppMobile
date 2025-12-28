package dk.sdu.mmmi.cbse.restapibackendandroid.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.DeviceToken;

@Service
public class DeviceTokenService {
    private final List<DeviceToken> deviceTokens = new ArrayList<>();

    public void addDeviceToken(String userId, String token) {
        deviceTokens.add(new DeviceToken(userId, token));
    }

    public List<String> getTokensUser(String userId) {
        return deviceTokens.stream()
                .filter(dt -> dt.getUserId().equals(userId))
                .map(DeviceToken::getToken)
                .collect(Collectors.toList());
    }
}
