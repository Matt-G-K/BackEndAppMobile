package dk.sdu.mmmi.cbse.restapibackendandroid;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private List<User> Users = new ArrayList<>();

    @GetMapping("/api/users")
    public List<User> getUsers() {
        return Users;
    }

    // @GetMapping("/api/users/{username}/history")
    // public List<String> getUserHistory(@PathVariable String username) {
    //     for (User user: Users) {
    //         if (Objects.equals(user.getUsername(), username)) {
    //             return user.getCarsRented();
    //         }
    //     }
    //     return new ArrayList<String>();
    // }

    @PostMapping("/api/users/create/{username}/{email}/{password}/{number}")
    public String createUser(@PathVariable String username, @PathVariable String email, @PathVariable String password, @PathVariable String number) {
        System.out.println("Creating user");
        ArrayList<String> emptyHistory = new ArrayList<>();
        User newUser = new User(username, email, password, number, emptyHistory);
        Users.add(newUser);
        return "Added user with username: "+username;
    }

    // @PutMapping("/api/rent/{id}/{username}")
    // public String rentCarUser(@PathVariable int id, @PathVariable String username) {
    //     System.out.println("got request with id: "+id+" and username: "+username);
    //     for (User user: Users) {
    //         System.out.println("Scanning users");
    //         if (Objects.equals(user.getUsername(), username)) {
    //             user.addCarsRented(String.valueOf(id));
    //             return "Added car with id: "+id+" to user: "+username+" list of rented cars";
    //         }
    //     }
    //     return "Error";
    // }
}
