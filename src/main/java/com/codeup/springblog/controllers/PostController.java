package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@GetMapping is shorter way, already hanbdling the GET request
//@PostMapping shorter way of handling POST request

@Controller
public class PostController {
    @GetMapping ("/posts")
    public String index(Model model) {
        List<Post> allPosts = new ArrayList<>();
        Post post2 = new Post(2, "test", "testing out code");
        Post post3 = new Post(3, "another test", "another test");
        Post post4 = new Post(4, "and another test", "yet another test");

        allPosts.add(post2);
        allPosts.add(post3);
        allPosts.add(post4);

        model.addAttribute("allPosts", allPosts);

        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String viewPost(@PathVariable long id, Model model) {
        Post post1 = new Post(1, "Regulus Spring", "Working on Spring in class");
        model.addAttribute("singlePost", post1);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String viewForm () {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }
}
