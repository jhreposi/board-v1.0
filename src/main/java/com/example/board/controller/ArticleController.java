package com.example.board.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import com.example.board.service.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ArticleController", value = "/board/*")
@MultipartConfig
public class ArticleController extends HttpServlet {
    Map<String, HttpService> commandMap;
    String prefix = "/WEB-INF/view/";

    public void init() {
        commandMap = new HashMap<>();
        commandMap.put("GET:list", new ListService());
        commandMap.put("POST:view", new ViewService());
        commandMap.put("GET:write", new WriteService());
        commandMap.put("POST:write",new WriteService());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        viewLink(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("do post");
        request.setCharacterEncoding("UTF-8");
        viewLink(request, response);
    }

    private void viewLink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceResult service = getService(request, response);

        if (service.getActionType().contains("dispatcher")) {
            System.out.println("dispatcher");
            request.getRequestDispatcher(prefix+service.getViewPath())
                    .forward(service.getRequest(),service.getResponse());
        }
        if (service.getActionType().contains("redirect")) {
            response.sendRedirect(request.getContextPath() + service.getViewPath());
        }
    }

    public ServiceResult getService(HttpServletRequest request, HttpServletResponse response) {
            return findTargetService(request).doService(request, response);
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