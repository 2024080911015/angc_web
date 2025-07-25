package com.example.demo3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

//【重要】这里的路径要和你的 JSP 文件中的 <c:url> 匹配！
// 如果 JSP 中是 /uploads2/，这里就是 /uploads2/*
@WebServlet("/uploads2/*")
public class FileServlet extends HttpServlet {
    private static final String UPLOAD_PATH = "D:" + File.separator + "uploads2";

    public void log(String message) {
        System.out.println("[FileServlet] " + message);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("--- doGet方法开始执行 ---");

        try {
            // 1. 获取请求的文件路径
            String requestedFile = request.getPathInfo();
            log("收到的请求路径 (PathInfo): " + requestedFile);

            if (requestedFile == null || requestedFile.equals("/")) {
                log("错误: 请求路径为空，无法确定文件名。");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            // 2. 获取纯粹的文件名 (移除开头的 "/")
            String fileName = requestedFile.substring(1);
            log("解析出的文件名: " + fileName);

            // 3. 构建文件的完整物理路径
            File file = new File(UPLOAD_PATH, fileName);
            log("尝试查找物理文件: " + file.getAbsolutePath());

            // 4. 检查文件是否存在
            if (!file.exists() || file.isDirectory()) {
                log("错误: 文件不存在或它是一个文件夹。");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            log("文件找到，大小为: " + file.length() + " 字节。");

            // 5. 设置响应头
            String mimeType = getServletContext().getMimeType(file.getAbsolutePath());
            if (mimeType == null) {
                mimeType = "application/octet-stream"; // 默认类型
            }
            response.setContentType(mimeType);
            response.setContentLengthLong(file.length());
            log("设置响应 Content-Type: " + mimeType);

            // 6. 将文件内容写入响应流
            log("正在将文件内容写入响应...");
            try (FileInputStream in = new FileInputStream(file);
                 OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
            log("文件发送完毕。");

        } catch (Exception e) {
            log("!!! 在执行doGet时发生了一个未知的严重异常 !!!");
            e.printStackTrace();
            // 不要在这里发送 response.sendError，因为可能响应已经提交了一部分
        }
        log("--- doGet方法结束 ---");
    }
}