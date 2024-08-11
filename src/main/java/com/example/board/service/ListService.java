
package com.example.board.service;

import com.example.board.dto.Articles;
import com.example.board.mapper.ArticleMapper;
import com.example.board.util.Paging;
import com.mysql.cj.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.example.board.util.Paging.parseNullInt;

public class ListService implements HttpService {
    SqlSession sqlSession;

    public ListService() {
        this.sqlSession = getSqlSession();
    }

    @Override
    public ServiceResult doService(HttpServletRequest request, HttpServletResponse response) {
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        Map<String, Object> map = optionToMap(request);

        int pageNum = parseNullInt(request.getParameter("pageNum"));
        int totalArticle = sqlSession.getMapper(ArticleMapper.class).countArticleOption(map);
        Paging paging = new Paging(pageNum, totalArticle, 5);
        map.put("paging", paging);

        List<Articles> articlesList = mapper.selectAllArticle(map);
        request.setAttribute("page", paging);
        request.setAttribute("articles", articlesList);

        return new ServiceResult("dispatcher","list.jsp",request,response);
    }
    Map<String, Object> optionToMap(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        String category = request.getParameter("category");
        String keyword = request.getParameter("keyword");
        map.put("startDate", setStartDate(start));
        map.put("endDate", setEndDate(end));
        map.put("category", category);
        map.put("keyword", keyword);
        return map;
    }

    String setStartDate(String startDate) {
        if (StringUtils.isNullOrEmpty(startDate)) {
            return LocalDate.now().minusYears(1).toString();
        }
        return startDate;
    }
    String setEndDate(String endDate) {
        if (StringUtils.isNullOrEmpty(endDate)) {
            return LocalDate.now().toString();
        }
        return endDate;
    }
}
