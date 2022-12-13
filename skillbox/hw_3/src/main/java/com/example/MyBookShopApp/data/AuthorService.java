package com.example.MyBookShopApp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorService {
    private final JdbcTemplate jdbcTemplate;
    private final static Character[] ALPHABET =
            new Character[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё',
                    'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н',
                    'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х',
                    'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AuthorEntity getAuthor(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM author WHERE id = ?",
                (rs, rowNum) -> new AuthorEntity()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name")),
                id
        );
    }

    public Map<Character, List<String>> getAuthorMapByFirstLetterOfSurname() {

        SortedMap<Character, List<String>> map = new TreeMap<>();
        for (Character letter : ALPHABET) {
            List<String> names = jdbcTemplate.queryForList("SELECT name FROM author WHERE name LIKE '" + letter + "%'", String.class);
            if (names.size() > 0) {
                map.put(letter, names);
            }
        }

        return map;
    }
}
