package com.example.MyBookShopApp.data.genre;

import com.example.MyBookShopApp.data.book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genres")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    private String name;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private List<Book> bookList = new ArrayList<>();

    @Transient
    private List<GenreEntity> childList = new ArrayList<>();

    public List<GenreEntity> getChildList() {
        return childList;
    }
    public void addNewChild(GenreEntity genre){
        childList.add(genre);
    }

    public void setChildList(List<GenreEntity> childList) {
        this.childList = childList;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
