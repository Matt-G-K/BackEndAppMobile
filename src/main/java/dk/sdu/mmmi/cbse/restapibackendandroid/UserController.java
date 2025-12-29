package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import dk.sdu.mmmi.cbse.restapibackendandroid.service.NotificationService;
import dk.sdu.mmmi.cbse.restapibackendandroid.service.NotificationSettingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private List<User> Users = new ArrayList<>();
    private GroupController groupController;

    private final NotificationService notificationService;
    private final NotificationSettingService notificationSettingService;

    public UserController(GroupController groupController, NotificationService notificationService, NotificationSettingService notificationSettingService) {
        this.groupController = groupController;
        this.notificationService = notificationService;
        this.notificationSettingService = notificationSettingService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return Users;
    }

    @GetMapping("/api/users/{username}/history")
    public List<Integer> getUserHistory(@PathVariable String username) {
        for (User user: Users) {
            if (Objects.equals(user.getUsername(), username)) {
                return user.getTransactionsMember();
            }
        }
        return new ArrayList<Integer>();
    }

    @PostMapping("/api/users/create/{username}/{email}/{password}")
    public String createUser(@PathVariable String username, @PathVariable String email, @PathVariable String password) {
        System.out.println("Creating user");
        ArrayList<Integer> emptyHistory = new ArrayList<>();
        User newUser = new User(username, email, password, emptyHistory, emptyHistory);
        Users.add(newUser);
        return "Added user with username: "+username;
    }

    @PutMapping("/api/user/addgroup/{id}/{username}")
    public String addGroup(@PathVariable int id, @PathVariable String username) {
        System.out.println("got request with id: "+id+" and username: "+username);

        // Temp solution to get group name
        String groupName = groupController.getGroupNameById(id);
        
        for (User user: Users) {
            System.out.println("Scanning users");
            if (Objects.equals(user.getUsername(), username) && !user.getGroupsMember().contains(id)) {
                // Send notification about group invitation
                notificationService.sendGroupInvitationNotification(user.getUserId(), groupName);

                user.addGroupMember(id);
                return "Added group with id: "+id+" to user: "+username+" to group " + groupName;
            } else if (Objects.equals(user.getUsername(), username) && user.getGroupsMember().contains(id)) {
                System.out.println("Group already associated to user");
                return "Group: "+id+" already associated to user: "+username;
            }
        }
        return "Error";
    }

    @PutMapping("/api/user/addtransaction/{id}/{username}")
    public String addTransaction(@PathVariable int id, @PathVariable String username) {
        System.out.println("got request with id: "+id+" and username: "+username);
        for (User user: Users) {
            System.out.println("Scanning users");
            if (Objects.equals(user.getUsername(), username) && !user.getTransactionsMember().contains(id)) {
                user.addTransactionMember(id);
                return "Added transaction with id: "+id+" to user: "+username+" list of rented cars";
            } else if (Objects.equals(user.getUsername(), username) && user.getTransactionsMember().contains(id)) {
                System.out.println("Transaction already associated to user");
                return "Transaction: "+id+" already associated to user: "+username;
            }
        }
        return "Error";
    }

    @PutMapping("/api/user/removegroup/{id}/{username}")
    public String removeGroup(@PathVariable int id, @PathVariable String username) {
        System.out.println("got request with id: "+id+" and username: "+username);
        for (User user: Users) {
            System.out.println("Scanning users");
            if (Objects.equals(user.getUsername(), username) && user.getGroupsMember().contains(id)) {
                user.removeGroupMember(id);
                return "Removed group with id: "+id+" to user: "+username+" list of rented cars";
            } else if (Objects.equals(user.getUsername(), username) && !user.getGroupsMember().contains(id)) {
                System.out.println("Group not associated to user");
                return "Group: "+id+" not associated to user: "+username;
            }
        }
        return "Error";
    }

    @PutMapping("/api/user/removetransaction/{id}/{username}")
    public String removeTransaction(@PathVariable int id, @PathVariable String username) {
        System.out.println("got request with id: "+id+" and username: "+username);
        for (User user: Users) {
            System.out.println("Scanning users");
            if (Objects.equals(user.getUsername(), username) && user.getTransactionsMember().contains(id)) {
                user.removeTransactionMember(id);
                return "Removed transaction with id: "+id+" to user: "+username+" list of rented cars";
            } else if (Objects.equals(user.getUsername(), username) && !user.getTransactionsMember().contains(id)) {
                System.out.println("Transaction not associated to user");
                return "Transaction: "+id+" not associated to user: "+username;
            }
        }
        return "Error";
    }

    // Temp solution until authentication is implemented
    @PutMapping("/api/user/setuserid/{username}/{userId}")
    public String setUserId(@PathVariable String username, @PathVariable String userId) {
        // Create default notification settings for the new user
        notificationSettingService.createDefaultSettings(userId);
        for (User user: Users) {
            if (Objects.equals(user.getUsername(), username)) {
                user.setUserId(userId);
                return "Set userId: "+userId+" for user: "+username;
            }
        }
        return "Error";
    }

}

