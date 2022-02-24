package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    //will return a single book with the matching title.
    Book findBookByTitle();

    // Custom query may require use of @Query annotation. uses HQL syntax
    @Query("from Book b where b.title = :bookTitle") // ':' notifies that it is a parameter
    List<Book>getBookByAuthor(@Param("bookTitle")String title);
}
