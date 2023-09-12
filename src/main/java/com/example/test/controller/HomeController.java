package com.example.test.controller;

import com.example.test.model.Blog;
import com.example.test.model.User;
import com.example.test.service.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@RestController
public class HomeController
{
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReplyService replyService;

    @GetMapping("/home")
    public ModelAndView goHome(HttpSession httpSession)
    {
        ModelAndView modelAndView = new ModelAndView("home");
        if(httpSession.getAttribute("user") != null)
        {
            modelAndView.addObject("user",(User)httpSession.getAttribute("user"));
        }
        List<Blog> blogList = blogService.getAll();
        modelAndView.addObject("blogs", blogList);
        modelAndView.addObject("pageTitle", "Home");
        return modelAndView;
    }

    @GetMapping("/blog/{id}")
    public ModelAndView blog(HttpSession httpSession, @PathVariable int id)
    {
        ModelAndView modelAndView = new ModelAndView("blog");
        if(httpSession.getAttribute("user") != null)
        {
            modelAndView.addObject("user",(User)httpSession.getAttribute("user"));
        }
        Blog blog = blogService.get(id);
        modelAndView.addObject("blog", blog);
        modelAndView.addObject("pageTitle", blog.getTitle());
        modelAndView.addObject("replyList", replyService.getReply(id));
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpSession httpSession, HttpServletResponse httpServletResponse)
    {
        ModelAndView modelAndView;
        if(httpSession.getAttribute("user") != null)
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Already Login");
            modelAndView.addObject("url", "/home");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        else
        {
            modelAndView = new ModelAndView("login");
            modelAndView.addObject("pageTitle", "Login");
        }
        return modelAndView;
    }

    @GetMapping("/signIn")
    public ModelAndView signIn(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        if(httpSession.getAttribute("user") != null)
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Already Login");
            modelAndView.addObject("url", "/home");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        else
        {
            modelAndView = new ModelAndView("signIn");
            modelAndView.addObject("pageTitle", "SignIn");
        }
        return modelAndView;
    }

    @GetMapping("/myInfo")
    public ModelAndView myInfo(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        User user = (User)httpSession.getAttribute("user");
        if( user != null)
        {
            modelAndView = new ModelAndView("userProfileInfo");
            modelAndView.addObject("user",user);
            modelAndView.addObject("thumbUp", userService.getThumbUp(user.getUser_id()));
            modelAndView.addObject("thumbDown", userService.getThumbDown(user.getUser_id()));
            modelAndView.addObject("pageTitle", "My Profile");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Logged Out");
            modelAndView.addObject("url", "/login");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/myPhone")
    public ModelAndView myPhone(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        if(httpSession.getAttribute("user") != null)
        {
            modelAndView = new ModelAndView("userProfilePhone");
            modelAndView.addObject("user",(User)httpSession.getAttribute("user"));
            modelAndView.addObject("pageTitle", "Update Phone");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Logged Out");
            modelAndView.addObject("url", "/login");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/myEmail")
    public ModelAndView myEmail(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        if(httpSession.getAttribute("user") != null)
        {
            modelAndView = new ModelAndView("userProfileEmail");
            modelAndView.addObject("user",(User)httpSession.getAttribute("user"));
            modelAndView.addObject("pageTitle", "Update E-mail");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Logged Out");
            modelAndView.addObject("url", "/login");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/resetPassword")
    public ModelAndView resetPassword(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        if(httpSession.getAttribute("user") != null)
        {
            modelAndView = new ModelAndView("userProfilePassword");
            modelAndView.addObject("user",(User)httpSession.getAttribute("user"));
            modelAndView.addObject("pageTitle", "Reset Password");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Logged Out");
            modelAndView.addObject("url", "/login");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/myBlog")
    public ModelAndView myBlog(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        if(httpSession.getAttribute("user") != null)
        {
            User user = (User)httpSession.getAttribute("user");
            modelAndView = new ModelAndView("userProfileMyBlog");
            modelAndView.addObject("user",user);
            List<Blog> blogList = blogService.getMyBlog(user.getUser_id());
            modelAndView.addObject("blogs", blogList);
            modelAndView.addObject("pageTitle", "My Blog");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Logged Out");
            modelAndView.addObject("url", "/login");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/postBlog")
    public ModelAndView postBlog(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        if(httpSession.getAttribute("user") != null)
        {
            User user = (User)httpSession.getAttribute("user");
            Blog blog = new Blog();
            System.out.println("######user_id is " + user.getUser_id());
            blog.setUserId(user.getUser_id());
            modelAndView = new ModelAndView("userProfileMyBlogView");
            modelAndView.addObject("blog",blog);
            modelAndView.addObject("user",user);
            modelAndView.addObject("pageTitle", "Post Blog");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Logged Out");
            modelAndView.addObject("url", "/login");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/myBlog/{id}")
    public ModelAndView update(HttpSession httpSession, @PathVariable int id)
    {
        ModelAndView modelAndView;
        User user = (User)httpSession.getAttribute("user");
        if(user != null)
        {
            Blog blog = blogService.get(id);
            if(blog!=null && blog.getUserId()==user.getUser_id())
            {
                modelAndView = new ModelAndView("userProfileMyBlogView");
                modelAndView.addObject("user",user);
                modelAndView.addObject("blog", blog);
                modelAndView.addObject("pageTitle", blog.getTitle());
            }
            else
            {
                modelAndView = new ModelAndView("error");
                modelAndView.addObject("error", "404 Can Not Find Blog Page");
                modelAndView.addObject("url", "/myBlog");
                modelAndView.addObject("pageTitle", "Error 404");
            }
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 You Are Logged Out");
            modelAndView.addObject("url", "/login");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/userBlog")
    public ModelAndView userBlog(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        User user = (User)httpSession.getAttribute("user");
        if(user != null && user.getRole().equals("admin"))
        {
            modelAndView = new ModelAndView("userProfileUserBlog");
            modelAndView.addObject("user",user);
            List<Blog> blogList = blogService.getUserBlog(user.getUser_id());
            modelAndView.addObject("blogs", blogList);
            modelAndView.addObject("pageTitle", "My Blog");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 Forbidden");
            modelAndView.addObject("url", "/User Blogs");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/userBlog/{id}")
    public ModelAndView userBlogView(HttpSession httpSession, @PathVariable int id)
    {
        ModelAndView modelAndView;
        User user = (User)httpSession.getAttribute("user");
        if(user != null && user.getRole().equals("admin"))
        {
            Blog blog = blogService.get(id);
            if(blog!=null)
            {
                modelAndView = new ModelAndView("userProfileUserBlogView");
                modelAndView.addObject("user",user);
                modelAndView.addObject("blog", blog);
                modelAndView.addObject("pageTitle", blog.getTitle());
            }
            else
            {
                modelAndView = new ModelAndView("error");
                modelAndView.addObject("error", "404 Can Not Find Blog Page");
                modelAndView.addObject("url", "/myBlog");
                modelAndView.addObject("pageTitle", "Error 404");
            }
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 Forbidden");
            modelAndView.addObject("url", "/myInfo");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/userList")
    public ModelAndView userList(HttpSession httpSession)
    {
        ModelAndView modelAndView;
        User user = (User)httpSession.getAttribute("user");
        if(user != null && user.getRole().equals("admin"))
        {
            modelAndView = new ModelAndView("userProfileUserList");
            modelAndView.addObject("user",user);
            List<User> userList = userService.getAll();
            modelAndView.addObject("userList", userList);
            modelAndView.addObject("pageTitle", "User List");
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 Forbidden");
            modelAndView.addObject("url", "/myInfo");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }

    @GetMapping("/userList/{id}")
    public ModelAndView userView(HttpSession httpSession, @PathVariable int id)
    {
        ModelAndView modelAndView;
        User sessionUser = (User)httpSession.getAttribute("user");
        if(sessionUser != null && sessionUser.getRole().equals("admin"))
        {
            User user = userService.get(id);
            if(user!=null)
            {
                modelAndView = new ModelAndView("userProfileUserView");
                modelAndView.addObject("user", sessionUser);
                modelAndView.addObject("tUser", user);
            }
            else
            {
                modelAndView = new ModelAndView("error");
                modelAndView.addObject("error", "404 Can Not Find User");
                modelAndView.addObject("url", "/myBlog");
                modelAndView.addObject("pageTitle", "Error 404");
            }
        }
        else
        {
            modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", "403 Forbidden");
            modelAndView.addObject("url", "/myInfo");
            modelAndView.addObject("pageTitle", "Error 403");
        }
        return modelAndView;
    }


}
