package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@GetMapping is shorter way, already hanbdling the GET request
//@PostMapping shorter way of handling POST request

@Controller
public class PostController {
    private PostRepository postDao;
    private UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping ("/posts")
    public String index(Model model) {
       model.addAttribute("allPosts", postDao.findAll());

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        model.addAttribute("singlePost", postDao.getById(id));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {
        Post posttoEdit = postDao.getById(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (posttoEdit.getUser().getId() == loggedInUser.getId()) {
            model.addAttribute("postToEdit", posttoEdit);
            return "posts/edit";
        } else {
            return "redirect:/posts";
        }

    }

    @PostMapping("/post{id}/edit")
    public String submitEdit(@ModelAttribute Post postToEdit) {
//        Post postToEdit = postDao.getById(id);
//        postToEdit.setTitle(title);
//        postToEdit.setBody(body);
        postDao.save(postToEdit);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String viewEditForm (Model model) {
        model.addAttribute("newPost", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post newPost) {
        //Post newPost = new Post(title, body);
        newPost.setUser(userDao.getById(1L));
        postDao.save(newPost);
        emailService.prepareAndSend(newPost,"New post was created", "You posted to the blog");
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}