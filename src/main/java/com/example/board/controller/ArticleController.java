package com.example.board.controller;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.board.model.Article;
import com.example.board.service.ArticleService;
import com.example.board.service.HttpService;
import com.example.board.service.ListService;
import com.example.board.service.ServiceResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet(name = "ArticleController", value = "/board/list")
public class ArticleController extends HttpServlet {
    ArticleService articleService;
    Map<String, HttpService> commandMap;
    SqlSessionFactory sqlSessionFactory;
    String prefix = "/WEB-INF/view/";

    public void init() {
        sqlSessionFactory = (SqlSessionFactory) getServletContext().getAttribute("sqlSessionFactory");
        commandMap = new HashMap<>();
        commandMap.put("GET:list", new ListService());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceResult service = getService(request, response);
        if (service.getActionType().contains("dispatcher")) {
            System.out.println(service.getRequest().getAttribute("articles").toString());
            request.getRequestDispatcher(prefix+service.getViewPath())
                    .forward(service.getRequest(),service.getResponse());
        }
        request.getRequestDispatcher("/WEB-INF/view/list.jsp").forward(request,response);
    }

    public ServiceResult getService(HttpServletRequest request, HttpServletResponse response) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return findTargetService(request).doService(request, response, sqlSession);
        }
    }
    private HttpService findTargetService(HttpServletRequest request) {
        String fullUri = String.valueOf(request.getRequestURI());
        String httpMethod = request.getMethod();
        String urlParam = fullUri.substring(fullUri.indexOf("/", 2) + 1);
        System.out.println(httpMethod + ":" + urlParam);
        
        return commandMap.get(httpMethod + ":" + urlParam);
    }
}