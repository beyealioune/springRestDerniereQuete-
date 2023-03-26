package com.wildcodeschool.queterest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wildcodeschool.queterest.modele.Book;

public interface BookRespository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingOrDescriptionContainingOrAuthorContaining(String title, String description, String author);
    
}
