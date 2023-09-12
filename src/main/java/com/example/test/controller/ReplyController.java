package com.example.test.controller;

import com.example.test.model.*;
import com.example.test.service.ReplyService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReplyController
{
    @Autowired
    private ReplyService replyService;

    @PostMapping("/postReply")
    public String add(@RequestBody Reply reply, HttpSession httpSession)
    {
        User user = (User)httpSession.getAttribute("user");
        if(user != null)
        {
            reply.setAuthor(user.getFirstName() + " " + user.getLastName());
            if(replyService.add(reply))
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
            return "You are already logged out";
        }

    }

    @PutMapping("/updateReply")
    public boolean update(@RequestBody Reply newReply)
    {
        return replyService.update(newReply);
    }

    @DeleteMapping("/deleteReply")
    public boolean delete(@RequestBody Reply reply)
    {
        return replyService.delete(reply.getId());
    }
}
