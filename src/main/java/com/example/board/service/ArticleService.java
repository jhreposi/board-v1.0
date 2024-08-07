package com.example.board.service;

import com.example.board.dto.Articles;
import com.example.board.mapper.ArticleMapper;
import com.example.board.model.Article;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class ArticleService {
    SqlSessionFactory sqlSessionFactory;

    public ArticleService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<Articles> getAllArticle() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ArticleMapper mapper = session.getMapper(ArticleMapper.class);
            return mapper.selectAllArticle();
        }
    }

}
