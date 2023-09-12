package com.example.test.dao;

import com.example.test.model.Blog;
import com.example.test.model.User;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository
{

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public User login(User user)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("From User where email='" + user.getEmail()+ "' AND password ='"+ user.getPassword()+"'", User.class);
        List<User> list = query.getResultList();
        if(list.size() == 1)
        {
            return list.get(0);
        }
        return null;
    }

    @Transactional
    public boolean signIn(User user)
    {
        entityManager.persist(user);
        return true;
    }

    @Transactional
    public boolean update(User newUser)
    {
        System.out.println(newUser);
        User user = entityManager.find(User.class, newUser.getUser_id());
        if(user!=null)
        {
            user.merge(newUser);
            System.out.println(user);
            entityManager.flush();
            return true;
        }
        return false;
    }

    @Transactional
    public boolean delete(int id)
    {
        User user = entityManager.find(User.class, id);
        if(user!=null)
        {
            entityManager.remove(user);
            return true;
        }
        return false;
    }

    @Transactional
    public List<User> getAll()
    {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("From User where role !='admin'", User.class);
        List<User> list = query.getResultList();
        return list;
    }

    @Transactional
    public User get(int user_id)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("From User where user_id =" + user_id, User.class);
        List<User> list = query.getResultList();
        if(list.size() == 1)
        {
            return list.get(0);
        }
        return null;
    }

    public int getThumbUp(int user_id)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "SELECT SUM(thumbUp) as total_thumb_up FROM Blog WHERE userId =" + user_id;
        Query<Long> query = currentSession.createQuery(sql);
        List<Long> list = query.getResultList();
        if(list.size() == 1)
        {
            return list.get(0).intValue();
        }
        return -1;
    }

    public int getThumbDown(int user_id)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        String sql = "SELECT SUM(thumbDown) as total_thumb_Down FROM Blog WHERE userId =" + user_id;
        Query<Long> query = currentSession.createQuery(sql);
        List<Long> list = query.getResultList();
        if(list.size() == 1)
        {
            return list.get(0).intValue();
        }
        return -1;
    }

}
