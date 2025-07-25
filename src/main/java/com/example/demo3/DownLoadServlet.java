package com.example.demo3;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownLoadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String UPLOAD_DICTIONARY = "D:/uploads2";
        String FileName = request.getParameter("file");


        File file = new File(UPLOAD_DICTIONARY, FileName);
        String mimeType = getServletContext().getMimeType(file.getPath());
        response.setContentType(mimeType);
        String encodedFileName = URLEncoder.encode(FileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);
        response.setContentLength((int) file.length());
        try (FileInputStream inStream = new FileInputStream(file);
             OutputStream outStream = response.getOutputStream()) {
            byte[] bytes = new byte[4096];
            while (inStream.read(bytes) != -1) {
                outStream.write(bytes);
            }
        } catch (IOException e) {
            System.out.println("下载失败");
            System.out.println(e);
        }
    }
}
