package com.example.test.service;

import com.example.test.dao.UserRepository;
import com.example.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public User login(User user)
    {
        return userRepository.login(user);
    }

    public Boolean signIn(User user)
    {
        user.setRole("user");
        return userRepository.signIn(user);
    }

    public Boolean update(User user)
    {
        return userRepository.update(user);
    }

    public Boolean delete(int id)
    {
        return userRepository.delete(id);
    }

    public List<User> getAll()
    {
        return userRepository.getAll();
    }

    public User get(int user_id)
    {
        return userRepository.get(user_id);
    }

    public int getThumbUp(int user_id)
    {
        return userRepository.getThumbUp(user_id);
    }

    public int getThumbDown(int user_id)
    {
        return userRepository.getThumbDown(user_id);
    }

}
