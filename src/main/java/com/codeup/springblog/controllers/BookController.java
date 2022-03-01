package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    private BookRepository bookDao;
    //injected Jpa into book controller
    public BookController(BookRepository bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("allBooks", bookDao.findAll());
        return "books/index";
    }

    @GetMapping("/books/create")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/create";
    }

    @PostMapping("/books/create")
    public String createBook(@ModelAttribute Book book) {
//        book.setAuthor(authorDao.getById(1L));
        bookDao.save(book);
        return "redirect:books";
    }
}
