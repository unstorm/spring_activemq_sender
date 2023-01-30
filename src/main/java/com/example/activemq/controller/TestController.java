package com.example.activemq.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.activemq.model.Book;
import com.example.activemq.repository.BookRepository;
import com.example.activemq.repository.UserRepository;

@RestController
public class TestController {
    
    @Autowired
	private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // @Autowired
    // private MongoTemplate template;


    @RequestMapping("/")  
    public String hello() {  
        
        System.out.println(userRepository.findByName("Brian"));

        // for (int i = 1; i < 100; i++) {
        //     Book book = new Book();
        //     book.setId(Integer.toString(i));
        //     book.setName("Brian"+i);
        //     book.setData("test");
        //     book.setTime(new Date());
        //     template.insert(book);
        // }

        return "Hello my Activemq";  
    }

    @GetMapping("/books")
    public ResponseEntity<Map<String, Object>> getAllBooks(
        @RequestParam(required= false) String name,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size) {

        try {
            List<Book> books = new ArrayList<Book>();
            Pageable paging = PageRequest.of(page, size);
            Page<Book> pageBooks;

            if (name == null) {
                pageBooks = bookRepository.findAll(paging);
            } else {
                pageBooks = bookRepository.findByNameContainingIgnoreCase(name, paging);
            }

            books = pageBooks.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("books", books);
            response.put("currentPage", pageBooks.getNumber());
            response.put("totalItems", pageBooks.getTotalElements());
            response.put("totalPages", pageBooks.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }       
    }
}
