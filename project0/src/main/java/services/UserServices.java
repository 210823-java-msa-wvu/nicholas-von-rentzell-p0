package services;

import models.User;
import repositories.UserRepo;

public class UserServices {

    UserRepo userRepo = new UserRepo();

    public boolean login(String username, String password) {

        User u = userRepo.getByUsername(username);

        if (u != null) {
            return username.equals(u.getUsername()) && password.equals(u.getPassword());
        }
        return false;
    }
}
