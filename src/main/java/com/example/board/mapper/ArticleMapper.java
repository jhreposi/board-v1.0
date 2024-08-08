package com.example.board.mapper;

import com.example.board.dto.Articles;
import com.example.board.model.Article;
import com.example.board.model.Comment;
import com.example.board.model.FileVo;

import java.util.List;

public interface ArticleMapper {
    List<Articles> selectAllArticle();
    int insertArticle(Article article);
    int insertFile(FileVo fileVo);
    Articles selectArticleById(int articleId);
    List<Comment> selectComments(int articleId);
    FileVo selectFile(int articleId);
}
