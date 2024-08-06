package com.example.board.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Map;

public interface HttpService {
    ServiceResult doService(HttpServletRequest request, HttpServletResponse response, SqlSession sqlSession);
}
