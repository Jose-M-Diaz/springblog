package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@GetMapping is shorter way, already hanbdling the GET request
//@PostMapping shorter way of handling POST request

@Controller
public class PostController {
    private PostRepository postDao;
    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping ("/posts")
    public String index(Model model) {
        List<Post> allPosts = postDao.findAll();
//        Post post2 = new Post(2, "test", "testing out code");
//        Post post3 = new Post(3, "another test", "another test");
//        Post post4 = new Post(4, "and another test", "yet another test");
//
//        allPosts.add(post2);
//        allPosts.add(post3);
//        allPosts.add(post4);

        model.addAttribute("allPosts", allPosts);
//        model.addAttribute("allPosts", postDao.findAll());

        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String viewPost(@PathVariable long id, Model model) {
        Post post1 = new Post(1, "Regulus Spring", "Working on Spring in class");
        model.addAttribute("singlePost", post1);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {
        Post postToEdit = postDao.getById(id);
        model.addAttribute("postToEdit", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/post{id}/edit")
    public String submitEdit(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body, @PathVariable long id) {
        Post postToEdit = postDao.getById(id);

        postToEdit.setTitle(title);
        postToEdit.setBody(body);
        postDao.save(postToEdit);

        return "redirect: /posts";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String viewForm () {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post newPost = new Post(title, body);
        postDao.save(newPost);

        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        Post postToDelete = postDao.getById(id);
        postDao.delete(postToDelete);

        return "redirect: /posts";
    }
}