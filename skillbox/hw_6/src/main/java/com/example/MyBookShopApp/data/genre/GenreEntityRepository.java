package com.example.MyBookShopApp.data.genre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreEntityRepository extends JpaRepository<GenreEntity, Integer> {
}