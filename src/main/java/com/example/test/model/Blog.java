package com.example.test.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Blog")
public class Blog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "title")
    private String title;
    @Column(name = "blog")
    private String blog;
    @Column(name = "date")
    private Date date;
    @Column(name = "thumb_up")
    private int thumbUp;
    @Column(name = "thumb_down")
    private int thumbDown;

    @Column(name="author")
    private String author;

    public Blog() {
    }

    public Blog(int id, int userId, String title, String blog, Date date, int thumbUp, int thumbDown, String author) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.blog = blog;
        this.date = date;
        this.thumbUp = thumbUp;
        this.thumbDown = thumbDown;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(int thumbUp) {
        this.thumbUp = thumbUp;
    }

    public int getThumbDown() {
        return thumbDown;
    }

    public void setThumbDown(int thumbDown) {
        this.thumbDown = thumbDown;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", blog='" + blog + '\'' +
                ", date=" + date +
                ", thumbUp=" + thumbUp +
                ", thumbDown=" + thumbDown +
                ", author='" + author + '\'' +
                '}';
    }

    public void merge(Blog blog)
    {
        this.title = blog.getTitle();
        this.blog = blog.getBlog();
        this.date = blog.getDate();
    }

    public void thumbUp()
    {
        thumbUp++;
    }

    public void thumbDown()
    {
        thumbDown++;
    }
}
