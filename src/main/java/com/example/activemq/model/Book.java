package com.example.activemq.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String name;
    private String data;
    private Date time;
}