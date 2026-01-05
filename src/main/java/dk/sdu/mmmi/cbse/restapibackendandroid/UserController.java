package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import dk.sdu.mmmi.cbse.restapibackendandroid.repositoy.UserStore;
import dk.sdu.mmmi.cbse.restapibackendandroid.service.NotificationService;
import dk.sdu.mmmi.cbse.restapibackendandroid.service.NotificationSettingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserStore Users;
    private GroupController groupController;

    private final NotificationService notificationService;
    private final NotificationSettingService notificationSettingService;
    private final String ip = "10.0.2.2";

    public UserController(GroupController groupController, NotificationService notificationService, NotificationSettingService notificationSettingService, UserStore Users) {
        this.groupController = groupController;
        this.Users = Users;
        this.notificationService = notificationService;
        this.notificationSettingService = notificationSettingService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return Users.getUsers();
    }

    @GetMapping("/api/users/{username}/history")
    public List<Integer> getUserHistory(@PathVariable String username) {
        for (User user: Users.getUsers()) {
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
        String profileImage = "http://"+ip+":8080/images/profile1.png";
        User newUser = new User(username, email, password, emptyHistory, emptyHistory, profileImage, 0, null);
        Users.addUser(newUser);
        return "Added user with username: "+username;
    }

    @PutMapping("/api/user/addgroup/{id}/{username}")
    public String addGroup(@PathVariable int id, @PathVariable String username) {
        System.out.println("got request with id: "+id+" and username: "+username);

        // Temp solution to get group name
        String groupName = groupController.getGroupNameById(id);
        
        for (User user: Users.getUsers()) {
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
        for (User user: Users.getUsers()) {
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
        for (User user: Users.getUsers()) {
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
        for (User user: Users.getUsers()) {
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
        for (User user: Users.getUsers()) {
            if (Objects.equals(user.getUsername(), username)) {
                user.setUserId(userId);
                return "Set userId: "+userId+" for user: "+username;
            }
        }
        return "Error";
    }

    @GetMapping("/api/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        for (User user: Users.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @PutMapping("/api/user/addcard/{username}/{cardnumber}/{expirydate}")
    public String addCard(@PathVariable String username, @PathVariable String cardnumber, @PathVariable Integer expirydate) {
        for (User user: Users.getUsers()) {
            if (user.getUsername().equals(username)) {
                Card newcard = new Card(cardnumber, expirydate);
                user.addCard(newcard);
                return "Succesfully added card: "+newcard.toString()+" to user: "+user.toString();
            }
        }
        return null;
    }

    @GetMapping("/api/user/getcards/{username}")
    public List<Card> getCards(@PathVariable String username) {
        for (User user: Users.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user.getCards();
            }
        }
        return null;
    }


    @PutMapping("/api/user/removecard/{username}/{id}")
    public String removeCard(@PathVariable String username, @PathVariable Integer id) {
        for (User user: Users.getUsers()) {
            if (user.getUsername().equals(username)) {
                for (Card card: user.getCards()) {
                    if (card.getId().equals(id)) {
                        user.removeCard(id);
                        return "Succesfully removed card: "+card.toString()+" from user: "+user.toString();
                    }
                }
                return "Could not find card with id: "+id+" in users card list.";
            }
        }
        return null;
    }

    @PutMapping("/api/user/addaccount/{username}/{id}/{accountname}/{regnum}/{accountnumber}")
    public String addAccount(@PathVariable String username, @PathVariable Integer id,
                             @PathVariable String accountname, @PathVariable Integer regnum, @PathVariable Integer accountnumber) {
        for (User user: Users.getUsers()) {
            if (user.getUsername().equals(username)) {
                Account newaccount = new Account(id, accountname, regnum, accountnumber);
                user.addAccount(newaccount);
                return "Succesfully added account: "+newaccount.toString()+" to user: "+user.toString();
            }
        }
        return null;
    }

    @PutMapping("/api/user/removeaccount/{username}/{id}")
    public String removeAccount(@PathVariable String username, @PathVariable Integer id) {
        for (User user: Users.getUsers()) {
            if (user.getUsername().equals(username)) {
                for (Account account: user.getAccounts()) {
                    if (account.getId().equals(id)) {
                        user.removeAccount(id);
                        return "Succesfully removed account: "+account.toString()+" from user: "+user.toString();
                    }
                }
                return "Could not find account with id: "+id+" in users card list.";
            }
        }
        return null;
    }

    @GetMapping("/api/user/getimage/{username}")
    public String getProfileImage(@PathVariable String username) {
        System.out.println("Fetching profile image for user: "+username);
        for(User user: Users.getUsers()) {
            if(user.getUsername().equals(username)) {
                return user.getProfileImage();
            } else {
                System.out.println("Not this user: "+user.getUsername());
            }
        }
        return "Error";
    }

    @PutMapping("api/user/setimage/{username}/{image}")
    public String setProfileImage(@PathVariable String username, @PathVariable int image) {
        System.out.println("Changing image for user: "+username+" to:"+image);
        for(User user: Users.getUsers()) {
            if(user.getUsername().equals(username)) {
                user.setProfileImage("http://"+ip+":8080/images/profile"+image+".png");
                return "Succesfully changed image to: "+image+" for user: "+username;
            } else {
                System.out.println("Not this user: "+user.getUsername());
            }
        }
        return "Error";
    }

    @GetMapping("/api/user/getphonenumber/{username}")
    public int getPhoneNumber(@PathVariable String username) {
        System.out.println("Fetching phonenumber for user: "+username);
        for(User user: Users.getUsers()) {
            if(user.getUsername().equals(username)) {
                return user.getPhoneNumber();
            } else {
                System.out.println("Not this user: "+user.getUsername());
            }
        }
        return 0;
    }

    @PutMapping("api/user/setphonenumber/{username}/{phonenumber}")
    public String setPhoneNumber(@PathVariable String username, @PathVariable int phonenumber) {
        System.out.println("Changing phonenumber for user: "+username+" to:"+phonenumber);
        for(User user: Users.getUsers()) {
            if(user.getUsername().equals(username)) {
                user.setPhoneNumber(phonenumber);
                return "Succesfully changed phonenumber to: "+phonenumber+" for user: "+username;
            } else {
                System.out.println("Not this user: "+user.getUsername());
            }
        }
        return "Error";
    }
}

