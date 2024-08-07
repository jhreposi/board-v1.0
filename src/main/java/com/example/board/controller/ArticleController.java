package com.example.board.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.example.board.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet(name = "ArticleController", value = "/board/*")
@MultipartConfig
public class ArticleController extends HttpServlet {
    Map<String, HttpService> commandMap;
    SqlSessionFactory sqlSessionFactory;
    String prefix = "/WEB-INF/view/";

    public void init() {
        sqlSessionFactory = (SqlSessionFactory) getServletContext().getAttribute("sqlSessionFactory");
        commandMap = new HashMap<>();
        commandMap.put("GET:list", new ListService());
        commandMap.put("GET:write", new WriteService());
        commandMap.put("POST:write",new WriteService());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServiceResult service = getService(request, response);

        if (service.getActionType().contains("dispatcher")) {
            request.getRequestDispatcher(prefix+service.getViewPath())
                    .forward(service.getRequest(),service.getResponse());
        }
        if (service.getActionType().contains("redirect")) {
            response.sendRedirect(request.getContextPath() + service.getViewPath());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ServiceResult service = getService(request, response);

        if (service.getActionType().contains("dispatcher")) {
            request.getRequestDispatcher(prefix+service.getViewPath())
                    .forward(service.getRequest(),service.getResponse());
        }
        if (service.getActionType().contains("redirect")) {
            response.sendRedirect(request.getContextPath() + service.getViewPath());
        }
    }

    public ServiceResult getService(HttpServletRequest request, HttpServletResponse response) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return findTargetService(request).doService(request, response, sqlSession);
        }
    }
    private HttpService findTargetService(HttpServletRequest request) {
        String fullUri = String.valueOf(request.getRequestURI());
        String httpMethod = request.getMethod();
        String urlParam = fullUri.substring(fullUri.indexOf("/", 2) + 1);

        String link = httpMethod + ":" + urlParam;
        if (urlParam.equals("/")) {
            link = "default";
        }
        
        return commandMap.get(link);
    }
}