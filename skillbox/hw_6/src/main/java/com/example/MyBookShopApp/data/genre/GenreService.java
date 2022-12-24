package com.example.MyBookShopApp.data.genre;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GenreService {
    private final GenreEntityRepository genreEntityRepository;

    public GenreService(GenreEntityRepository genreEntityRepository) {
        this.genreEntityRepository = genreEntityRepository;
    }

    public GenreEntity getGenre(Integer genreId) {
        return genreEntityRepository.findById(genreId).get();
    }

    public List<GenreEntity> getGenres() {
        return genreEntityRepository.findAll();
    }

    public List<GenreEntity> getGenresPath(Integer genreChildId) {
        Integer genreId = genreChildId;
        List<GenreEntity> list = new ArrayList<>();
        while (true) {
            GenreEntity genre = genreEntityRepository.findById(genreId).get();
            list.add(genre);
            if (genre.getParentId() == 0) {
                Collections.reverse(list);
                return list;
            }
            genreId = genre.getParentId();
        }
    }
}
