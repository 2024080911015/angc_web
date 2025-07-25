package com.example.demo3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;

@WebServlet("/uploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    public void log(String message) {
        System.out.println("[FileUploadServlet] " + message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("--- doPost方法开始执行 ---");

        // 设置响应编码
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        try {
            // 1. 定义上传路径
            String uploadPath = "D:" + File.separator + "uploads2";
            File uploadDir = new File(uploadPath);
            log("上传目标文件夹: " + uploadDir.getAbsolutePath());

            // 2. 如果文件夹不存在，则尝试创建
            if (!uploadDir.exists()) {
                log("文件夹不存在，正在尝试创建...");
                if (uploadDir.mkdir()) {
                    log("文件夹创建成功。");
                } else {
                    log("错误: 文件夹创建失败! 请检查权限或路径。");
                    response.getWriter().println("上传失败：无法创建服务器存储目录。");
                    return;
                }
            }

            // 3. 从请求中获取文件部分
            log("正在从请求中获取名为 'files' 的 Part...");
            Part filePart = request.getPart("files");

            if (filePart == null) {
                log("错误: 未能获取到名为 'files' 的 Part，请检查前端 <input> 的 name 属性。");
                response.getWriter().println("上传失败：请求中不包含文件数据。");
                return;
            }

            // 4. 获取文件名并准备写入
            String fileName = filePart.getSubmittedFileName();
            log("获取到上传的文件名: " + fileName);

            String destination = uploadPath + File.separator + fileName;
            log("准备将文件写入到: " + destination);

            // 5. 执行写入操作
            filePart.write(destination);

            log("文件写入成功!");
            response.getWriter().println("文件 " + fileName + " 上传成功！");

        } catch (Exception e) {
            log("!!! 在执行doPost时发生了一个未知的严重异常 !!!");
            e.printStackTrace();
            response.getWriter().println("上传文件时发生严重错误，请查看服务器日志。");
        }
        log("--- doPost方法结束 ---");
    }
}
