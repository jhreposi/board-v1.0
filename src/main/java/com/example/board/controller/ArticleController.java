package com.example.board.controller;

import java.io.*;
import java.util.List;

import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet(name = "ArticleController", value = "/board/list")
public class ArticleController extends HttpServlet {
    ArticleService articleService;
    private SqlSessionFactory sqlSessionFactory;

    public void init() {
        sqlSessionFactory = (SqlSessionFactory) getServletContext().getAttribute("sqlSessionFactory");
        articleService = new ArticleService(sqlSessionFactory);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Article> articles = articleService.getAllArticle();
        System.out.println("test" + articles.get(0).getAuthor());
        request.getRequestDispatcher("/WEB-INF/view/list.jsp").forward(request,response);
    }

    public void destroy() {
    }
}