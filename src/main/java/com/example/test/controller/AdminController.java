package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.BlogService;
import com.example.test.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/")
public class AdminController
{
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;


    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user, HttpSession httpSession)
    {
        User sessionUser = (User) httpSession.getAttribute("user");
        if(sessionUser != null && sessionUser.getRole().equals("admin"))
        {
            if(userService.update(user))
            {
                return "success";
            }
            else
            {
                return "failed";
            }
        }
        else
        {
            return "You are not Admin";
        }
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody User user, HttpSession httpSession)
    {
        User sessionUser = (User)httpSession.getAttribute("user");
        if((sessionUser != null)&&sessionUser.getRole().equals("admin"))
        {
            if(userService.delete(user.getUser_id()))
            {
                return "success";
            }
            else
            {
                return "failed";
            }
        }
        else
        {
            return "failed";
        }
    }

    @DeleteMapping("/deleteBlog")
    public String deleteUserBlog(@RequestBody Blog blog, HttpSession httpSession)
    {
        User user = (User)httpSession.getAttribute("user");
        if((user != null)&&user.getRole().equals("admin"))
        {
            if(blogService.delete(blog.getId()))
            {
                return "success";
            }
            else
            {
                return "failed";
            }
        }
        else
        {
            return "failed";
        }
    }
}
