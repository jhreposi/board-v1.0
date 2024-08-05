package com.example.board.controller;

import java.io.*;

import com.example.board.service.ArticleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ArticleController", value = "/board/list")
public class ArticleController extends HttpServlet {
    private String message;
    ArticleService articleService;

    public void init() {
        message = "list test!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/view/list.jsp").forward(request,response);
    }

    public void destroy() {
    }
}