package com.example.test.dao;

import com.example.test.model.Blog;
import com.example.test.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BlogRepository
{
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Blog> getAll()
    {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Blog> query = currentSession.createQuery("From Blog", Blog.class);
        List<Blog> list = query.getResultList();
        return list;
    }

    @Transactional
    public List<Blog> getUserBlog(int userId)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Blog> query = currentSession.createQuery("From Blog where userId !="+ userId, Blog.class);
        List<Blog> list = query.getResultList();
        return list;
    }

    @Transactional
    public Blog get(int id)
    {
        return entityManager.find(Blog.class,id);
    }

    @Transactional
    public List<Blog> getMyBlog(int userId)
    {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Blog> query = currentSession.createQuery("From Blog where userId="+ userId, Blog.class);
        List<Blog> list = query.getResultList();
        return list;
    }

    @Transactional
    public boolean add(Blog blog)
    {
        entityManager.persist(blog);
        return true;
    }

    @Transactional
    public boolean update(Blog newblog)
    {
        Blog blog = entityManager.find(Blog.class, newblog.getId());
        if(blog!=null)
        {
            blog.merge(newblog);
            entityManager.flush();
            return true;
        }
        return true;
    }

    @Transactional
    public boolean delete(int id)
    {
        Blog blog = entityManager.find(Blog.class,id);
        if(blog!=null)
        {
            entityManager.remove(blog);
            return true;
        }
        return false;
    }

    @Transactional
    public int thumbUp(int id)
    {
        Blog blog = entityManager.find(Blog.class,id);
        if(blog!=null)
        {
            blog.thumbUp();
            entityManager.flush();
            return blog.getThumbUp();
        }
        return -1;
    }

    @Transactional
    public int thumbDown(int id)
    {
        Blog blog = entityManager.find(Blog.class,id);
        if(blog!=null)
        {
            blog.thumbDown();
            entityManager.flush();
            return blog.getThumbDown();
        }
        return -1;
    }
}
