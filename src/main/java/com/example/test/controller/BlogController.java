package com.example.test.controller;


import com.example.test.model.*;
import com.example.test.service.BlogService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController
{
    @Autowired
    private BlogService blogService;

    @PostMapping("/postBlog")
    public String add(@RequestBody Blog blog, HttpSession httpSession)
    {
        User user = (User)httpSession.getAttribute("user");
        if((user == null)||(blog.getUserId() != user.getUser_id()))
        {
            return "failed";
        }
        else if(blogService.add(blog))
        {
            return "success";
        }
        else
        {
            return "failed";
        }
    }

    @PutMapping("/updateBlog")
    public String update(@RequestBody Blog blog, HttpSession httpSession)
    {
        User user = (User)httpSession.getAttribute("user");
        if((user == null)||(blog.getUserId() != user.getUser_id()))
        {
            return "failed";
        }
        else if(blogService.update(blog))
        {
            return "success";
        }
        else
        {
            return "failed";
        }
    }

    @DeleteMapping("/deleteBlog")
    public String delete(@RequestBody Blog blog, HttpSession httpSession)
    {
        User user = (User)httpSession.getAttribute("user");
        if((user == null)||(blog.getUserId() != user.getUser_id()))
        {
            return "failed";
        }
        else if(blogService.delete(blog.getId()))
        {
            return "success";
        }
        else
        {
            return "failed";
        }
    }

    @GetMapping("/thumbUp/{id}")
    public int thumbUp(@PathVariable int id)
    {
        return blogService.thumbUp(id);
    }

    @GetMapping("/thumbDown/{id}")
    public int thumbDown(@PathVariable int id)
    {
        return blogService.thumbDown(id);
    }
}
