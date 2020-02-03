package com.rest.webservices.restfulwebservices.services;

import com.rest.webservices.restfulwebservices.models.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 4;

    static {
        users.add(new User(1, "Mônica", new Date()));
        users.add(new User(2, "Cebolinha", new Date()));
        users.add(new User(3, "Cascão", new Date()));
        users.add(new User(4, "Magali", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User newUser) {

        if(newUser.getId() == null)
            newUser.setId(++usersCount);

        users.add(newUser);
        return newUser;
    }

    public User findOne(int id) {

        for(User user : users)
            if(user.getId() == id)
                return user;

         return null;
    }

    public User deleteById( int id) {

        Iterator<User> userIterator = users.iterator();
        User currentUser;

        while (userIterator.hasNext()) {

            currentUser = userIterator.next();
            if(currentUser.getId() == id) {
                userIterator.remove();
                return currentUser;
            }

        }

        return null;
    }

}
