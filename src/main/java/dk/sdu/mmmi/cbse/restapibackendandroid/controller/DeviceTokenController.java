package dk.sdu.mmmi.cbse.restapibackendandroid.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.DeviceToken;
import dk.sdu.mmmi.cbse.restapibackendandroid.service.DeviceTokenService;


@RestController
@CrossOrigin(origins = "*")
public class DeviceTokenController {
    
    private final DeviceTokenService deviceTokenService;

    
    public DeviceTokenController(DeviceTokenService deviceTokenService) {
        this.deviceTokenService = deviceTokenService;
    }

    @PutMapping("/api/devicetoken/adddevicetoken")
    public String addDeviceToken(@RequestBody DeviceToken deviceToken) {
        System.out.println("Received device token for userId: " + deviceToken.getUserId() + " with token " + deviceToken.getToken());
        deviceTokenService.addDeviceToken(deviceToken.getUserId(), deviceToken.getToken());
        return "Device token saved successfully";

    }

    @GetMapping("/api/devicetoken/gettokens/{userId}")
    public String getAllDeviceTokens(@PathVariable String userId) {
        var tokens = deviceTokenService.getTokensUser(userId);
        return "Retrieved " + tokens.size() + " tokens for user '" + userId + "'";
    }
}
