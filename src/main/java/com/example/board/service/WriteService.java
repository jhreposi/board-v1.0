package com.example.board.service;

import com.example.board.mapper.ArticleMapper;
import com.example.board.model.Article;
import com.example.board.model.FileVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.ibatis.session.SqlSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.UUID;

public class WriteService implements HttpService {
    // TODO: 2024-08-07 sqlsession 멤버변수로
    @Override
    public ServiceResult doService(HttpServletRequest request, HttpServletResponse response, SqlSession sqlSession) {
        if (request.getMethod().equals("GET")) {
            return new ServiceResult("dispatcher","write.jsp",request,response);
        } else if (request.getMethod().equals("POST")){
            int addedArticleId = articleAdd(request, sqlSession);
            FileVo fileVo = null;
            // TODO: 2024-08-07 조건 메서드화하기 
            if (request.getContentType() != null && request.getContentType().toLowerCase().startsWith("multipart/")) {
                try {
                    fileVo = fileService(request);
                    fileAdd(fileVo, addedArticleId, sqlSession);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return new ServiceResult("redirect", "/board/list",request,response);
        }
        return null;
    }
    int articleAdd(HttpServletRequest request, SqlSession sqlSession) {
        Article article = new Article();
        article.setAuthor(request.getParameter("author"));
        article.setTitle(request.getParameter("title"));
        article.setContent(request.getParameter("content"));
        article.setPassword(request.getParameter("password"));
        int result = sqlSession.getMapper(ArticleMapper.class).insertArticle(article);
//        if (result > 1) {
//            sqlSession.commit();
//        }
        return article.getId();
    }
    
    FileVo fileService(HttpServletRequest request) throws ServletException, IOException {
        FileVo fileVo = new FileVo();
        Collection<Part> parts = request.getParts();
        String attacheDir = "E:\\attaches";

        String uploadDir = attacheDir + File.separator;
        File uploadDirFile = new File(uploadDir);

        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }
        for (Part part : parts) {
            //todo 조건 유틸파일로 재정의하기
            if (part.getSubmittedFileName() != null && !part.getSubmittedFileName().isEmpty()) {
                String originalName = part.getSubmittedFileName();
                String uuidName = UUID.randomUUID() + "_" + originalName;

                File file = new
                        File(uploadDir + uuidName);
                try (InputStream inputStream = part.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
                fileVo.setUuidName(uuidName);
                fileVo.setOriginalName(originalName);
                fileVo.setDir(uploadDir);
            }
        }
        return fileVo;
    }

    int fileAdd(FileVo fileVo, int articleId, SqlSession sqlSession) {
        fileVo.setArticleId(articleId);
        return sqlSession.getMapper(ArticleMapper.class).insertFile(fileVo);
    }

}
