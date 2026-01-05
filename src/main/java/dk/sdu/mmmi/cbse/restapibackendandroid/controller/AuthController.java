package dk.sdu.mmmi.cbse.restapibackendandroid.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.dto.UserResponseDTO;

import dk.sdu.mmmi.cbse.restapibackendandroid.model.dto.LoginRequestDTO;
import dk.sdu.mmmi.cbse.restapibackendandroid.model.dto.RegisterRequestDTO;
import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.UserStore;
import dk.sdu.mmmi.cbse.restapibackendandroid.User;

@RestController
@RequestMapping("")
public class AuthController {
    
   private final UserStore store;

    public AuthController(UserStore store) {
        this.store = store;
    }
    @PostMapping("/api/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO req) {
        if (store.getUserByUsername(req.username()) != null) {
            return ResponseEntity.status(400).body("Username already exists");
        }
        if (store.getUserByUsername(req.username()) != null) {
            return ResponseEntity.status(400).body("Username already exists");
        }
        User newUser = new User(
            req.username(), 
            req.email(),
            req.password(),
            null,
            null,
            null,
            req.phoneNumber(),
            null);

        store.addUser(newUser);
        return ResponseEntity.ok(new UserResponseDTO(newUser.getUserId(), newUser.getUsername(), newUser.getEmail(), newUser.getPhoneNumber()));
    }


    @PostMapping("api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO req) {
        User user = store.getUserByUsername(req.username());
        if (user == null || !user.getPassword().equals(req.password())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        return ResponseEntity.ok(new UserResponseDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getPhoneNumber()));
    }
}
