package com.example.board.service;

import com.example.board.mapper.ArticleMapper;
import com.example.board.model.Article;

import java.util.List;

public class ArticleService {
    MyBatisInitializer sqlsession;
    ArticleMapper articleMapper;

    List<Article> getAllArticle() {
        return articleMapper.selectAllArticle();
    }

}
