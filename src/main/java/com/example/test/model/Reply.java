package com.example.test.model;

import jakarta.persistence.*;

import java.sql.Date;
@Entity
@Table(name="Reply")
public class Reply
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "blog_id")
    private int blog_id;
    @Column(name = "reply_id")
    private int reply_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "author")
    private String author;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private Date date;

    public Reply() {
    }

    public Reply(int id, int blog_id, int reply_id, int user_id, String author, String text, Date date) {
        this.id = id;
        this.blog_id = blog_id;
        this.reply_id = reply_id;
        this.user_id = user_id;
        this.author = author;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", blog_id=" + blog_id +
                ", reply_id=" + reply_id +
                ", user_id=" + user_id +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
