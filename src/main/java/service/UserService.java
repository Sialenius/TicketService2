package service;

import DAO.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import view.Printable;

import java.util.List;
import java.util.UUID;

@Component
@Transactional
public class UserService implements Printable {

    @Autowired
    private UserDAO userDAO;

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public void register(User user) {
        userDAO.save(user);
    }

    public User fetchByUserID(UUID userID) {
        User user = userDAO.fetchByID(userID);
        if (user == null) {
            System.out.println("The user with ID: '" + userID + "' doesn't exist.");
            System.exit(0);
        }
        return user;
    }

    public User fetchByName(String name) {
        return userDAO.fetchByName(name);
    }

    public void updateUserID(UUID userID, UUID newID) {
       fetchByUserID(userID);

       userDAO.updateID(userID, newID);
    }

    public void removeUser(UUID userID) {
        fetchByUserID(userID);

        userDAO.delete(userID);
    }

    public void deleteAllUsers() {
        userDAO.deleteAll();
    }

    @Override
    public void printInformation() {
        System.out.println("=========================== TABLE 'USERS_INFO' ==========================\n" +
                "                  id                 |  name  | creation_date | user_role  \n" +
                "--------------------------------------------------------------");
        if (userDAO.getAll().isEmpty()) {
            System.out.println("empty\n");
        } else {
            for (User u: userDAO.getAll()) {
                System.out.println(u.getId() + " | " + u.getName() + " | " + u.getCreationDate() + " | " + u.getRole());
            }
            System.out.print('\n');
        }
    }


}
