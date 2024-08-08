package com.example.board.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

public class ViewService implements HttpService {
    SqlSession sqlSession;

    public ViewService() {
        this.sqlSession = getSqlSession();
    }

    @Override
    public ServiceResult doService(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
