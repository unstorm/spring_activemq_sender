package com.example.activemq.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.activemq.model.Book;

public interface BookRepository extends MongoRepository<Book,String> {
    public Book findByName(String name);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
