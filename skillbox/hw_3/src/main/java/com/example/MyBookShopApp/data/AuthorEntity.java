package com.example.MyBookShopApp.data;

public class AuthorEntity {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public AuthorEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorEntity setName(String name) {
        this.name = name;
        return this;
    }
}
