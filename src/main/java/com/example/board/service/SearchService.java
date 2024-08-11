package com.example.board.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchService implements HttpService {
    @Override
    public ServiceResult doService(HttpServletRequest request, HttpServletResponse response) {
       String start = request.getParameter("start");
       String end = request.getParameter("end");
       String category = request.getParameter("category");
       String keyword = request.getParameter("keyword");
       return new ServiceResult("dispatcher","list.jsp",request,response);
    }
}
