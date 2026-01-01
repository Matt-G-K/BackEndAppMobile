package dk.sdu.mmmi.cbse.restapibackendandroid.repositoy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dk.sdu.mmmi.cbse.restapibackendandroid.User;

@Component
public class UserStore {
    private List<User> Users = new ArrayList<>();

    public List<User> getUsers() {
        return Users;
    }

    public void addUser(User user) {
        Users.add(user);
    }

    public User getUserById(String id) {
        for (User user : Users) {
            if (user.getUserId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : Users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

}
