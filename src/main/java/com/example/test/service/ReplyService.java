package com.example.test.service;

import com.example.test.dao.ReplyRepository;
import com.example.test.model.Reply;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReplyService
{
    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> getReply(int blog_id)
    {
        return replyRepository.getReply(blog_id);
    }

    public boolean add(Reply reply)
    {
        java.util.Date date  = new java.util.Date();
        reply.setDate(new java.sql.Date(date.getTime()));
        return replyRepository.add(reply);
    }

    public boolean update(Reply newReply)
    {
        return replyRepository.update(newReply);
    }

    public boolean delete(int id)
    {
        return replyRepository.delete(id);
    }

}
