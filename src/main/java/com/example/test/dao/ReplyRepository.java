package com.example.test.dao;

import com.example.test.model.*;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReplyRepository
{
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Reply> getReply(int blog_id)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Reply> query = currentSession.createQuery("From Reply where blog_id="+ blog_id, Reply.class);
        List<Reply> list = query.getResultList();
        return list;
    }

    @Transactional
    public boolean add(Reply reply)
    {
        System.out.println(reply);
        entityManager.persist(reply);
        return true;
    }

    @Transactional
    public boolean update(Reply newReply)
    {
        Reply reply = entityManager.find(Reply.class, newReply.getId());
        if(reply!=null)
        {
            reply.setText(newReply.getText());
            entityManager.flush();
            return true;
        }
        return true;
    }

    @Transactional
    public boolean delete(int id)
    {
        return false;
    }
}
