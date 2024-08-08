package com.example.board.service;

import com.example.board.dto.Articles;
import com.example.board.mapper.ArticleMapper;
import com.example.board.model.Article;
import com.example.board.model.Comment;
import com.example.board.model.FileVo;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ViewService implements HttpService {
    SqlSession sqlSession;

    public ViewService() {
        this.sqlSession = getSqlSession();
    }

    @Override
    public ServiceResult doService(HttpServletRequest request, HttpServletResponse response) {
//        Article articleForId;
//        try {
//             articleForId = new Gson().fromJson(request.getReader(), Article.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        int articleIdTemp = articleForId.getId();
        int articleId = Integer.parseInt(request.getParameter("id"));
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);

        Articles articleView = mapper.selectArticleById(articleId);
        List<Comment> articleComment = mapper.selectComments(articleId);
        FileVo articleFile = mapper.selectFiles(articleId);
        request.setAttribute("article", articleView);
        request.setAttribute("comments", articleComment);
        request.setAttribute("file", articleFile);

        return new ServiceResult("dispatcher", "articleOne.jsp",request,response);
    }
    public FileVo getFileById(int fileId) {
        return sqlSession.getMapper(ArticleMapper.class).selectFile(fileId);
    }
}
