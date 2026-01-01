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

}
