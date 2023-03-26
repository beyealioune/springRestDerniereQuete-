package com.wildcodeschool.queterest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.queterest.modele.Book;
import com.wildcodeschool.queterest.repository.BookRespository;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    
    @Autowired
   BookRespository bookRespository;

    @GetMapping("/books")
    public List<Book> index(){
        return bookRespository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable Long id){
        return bookRespository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRespository.findByTitleContainingOrDescriptionContainingOrAuthorContaining(searchTerm, searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRespository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book){
        // getting Book
        Book bookToUpdate = bookRespository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        bookToUpdate.setAuthor(book.getAuthor());

        return bookRespository.save(bookToUpdate);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id){
        bookRespository.deleteById(id);
        return true;
    }
    
}
