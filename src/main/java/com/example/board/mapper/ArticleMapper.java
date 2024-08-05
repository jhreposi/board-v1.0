package com.example.board.mapper;

import com.example.board.model.Article;

import java.util.List;

public interface ArticleMapper {
    List<Article> selectAllArticle();
}
