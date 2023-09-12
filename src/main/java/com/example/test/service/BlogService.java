package com.example.test.service;

import com.example.test.dao.BlogRepository;
import com.example.test.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class BlogService
{
    @Autowired
    private BlogRepository blogRepository;
    private static int blogId;

    BlogService()
    {

    }

    public List<Blog> getAll()
    {
        return blogRepository.getAll();
    }

    public Blog get(int id)
    {
        return blogRepository.get(id);
    }

    public List<Blog> getUserBlog(int userId)
    {
        return blogRepository.getUserBlog(userId);
    }

    public List<Blog> getMyBlog(int userId)
    {
        return blogRepository.getMyBlog(userId);
    }

    public boolean add(Blog blog)
    {
        java.util.Date date  = new java.util.Date();
        blog.setDate(new java.sql.Date(date.getTime()));
        blog.setThumbDown(0);
        blog.setThumbUp(0);
        return blogRepository.add(blog);
    }

    public boolean update(Blog blog)
    {
        java.util.Date date  = new java.util.Date();
        blog.setDate(new java.sql.Date(date.getTime()));
        return blogRepository.update(blog);
    }

    public boolean delete(int id)
    {
        return blogRepository.delete(id);
    }

    public int thumbUp(int id)
    {
        return blogRepository.thumbUp(id);
    }

    public int thumbDown(int id)
    {
        return blogRepository.thumbDown(id);
    }
}
