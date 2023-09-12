package com.example.test.controller;

import com.example.test.model.User;
import com.example.test.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession httpSession)
    {
        User loginUser = userService.login(user);
        if(loginUser != null)
        {
            System.out.println("#######################");
            System.out.println(loginUser);
            httpSession.setAttribute("user", loginUser);
            return "success";
        }
        else
        {
            return "failed";
        }
    }

    @PostMapping("/signIn")
    public String signIn(@RequestBody User user)
    {
        if(userService.signIn(user))
        {
            return "success";
        }
        else
        {
            return "failed";
        }
    }

    @PutMapping("/updateUser")
    public String update(@RequestBody User user, HttpSession httpSession)
    {
        User sessionUser = (User) httpSession.getAttribute("user");
        if(sessionUser == null)
        {
            return "You are Logged out";
        }
        else if(userService.update(user))
        {
            sessionUser.merge(user);
            httpSession.setAttribute("user", sessionUser);
            return "success";
        }
        else
        {
            return "failed";
        }
    }

    @PutMapping("/resetPassword")
    public String resetPassword(@RequestParam(value="password") String password, @RequestParam(value="oldPassword") String oldPassword, HttpSession httpSession)
    {
        User sessionUser = (User) httpSession.getAttribute("user");
        if(sessionUser == null)
        {
            return "You are Logged out";
        }
        else if (!sessionUser.getPassword().equals(oldPassword))
        {
            return "You Password Is Incorrect";
        }
        sessionUser.setPassword(password);
        if(userService.update(sessionUser))
        {
            httpSession.setAttribute("user", sessionUser);
            return "success";
        }
        else
        {
            return "failed";
        }
    }

    @GetMapping("/logOut")
    public void logOut(HttpSession httpSession, HttpServletResponse httpServletResponse) throws IOException {
        httpSession.removeAttribute("user");
        httpServletResponse.sendRedirect("/home");
    }


}
