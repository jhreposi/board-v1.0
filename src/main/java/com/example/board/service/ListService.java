
package com.example.board.service;

import com.example.board.mapper.ArticleMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;

public class ListService implements HttpService {
    SqlSession sqlSession;

    public ListService() {
        this.sqlSession = getSqlSession();
    }

    @Override
    public ServiceResult doService(HttpServletRequest request, HttpServletResponse response) {
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        request.setAttribute("articles", mapper.selectAllArticle());

        return new ServiceResult("dispatcher","list.jsp",request,response);
    }
}
