package com.example.board.controller;

import java.io.*;

import com.example.board.model.FileVo;
import com.example.board.service.ArticleService;
import com.example.board.service.ViewService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "FileDownloadServletServlet", value = "/file/download")
public class FileDownloadServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int fileId = Integer.parseInt(request.getParameter("fileId"));
        FileVo fileVo = new ViewService().getFileById(fileId);
        String filePath = fileVo.getDir() + fileVo.getUuidName();

        File file = new File(filePath);

        if(file.exists()) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename="+fileVo.getOriginalName());
            ServletOutputStream outputStream = response.getOutputStream();

            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                fileInputStream.close();
                outputStream.close();
            }
        }

    }

    public void destroy() {
    }
}