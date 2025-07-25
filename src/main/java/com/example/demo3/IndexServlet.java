package com.example.demo3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("")
public class IndexServlet extends HelloServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            String uploadPath ="D:/uploads2";
            File uploadDir =new File(uploadPath);
            if (!uploadDir.exists()) {
                log("错误: 文件夹不存在! 请确认路径是否正确。");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "错误: 服务器上的上传目录不存在。");
                return;
            }
            if (!uploadDir.isDirectory()) {
                log("错误: 指定路径不是一个文件夹!");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "错误: 服务器上的上传路径不是一个有效目录。");
                return;
            }
            // 3. 获取文件列表 (Model)
            log("正在尝试列出文件夹中的所有文件...");
            File[] fileArray = uploadDir.listFiles();
            List<File> fileList = null;

            // 4. 检查 listFiles() 的结果，如果因为权限等问题，它会返回 null
            if (fileArray == null) {
                log("严重错误: listFiles() 返回了 null! 这通常是由于操作系统的文件权限问题导致的。");
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "错误: 服务器无法读取上传目录，可能是权限不足。");
                return;
            }

            log("成功获取到 " + fileArray.length + " 个项目 (包含文件和文件夹)。");

            // 过滤掉文件夹，只保留文件
            fileList = Arrays.stream(fileArray)
                    .filter(File::isFile)
                    .collect(Collectors.toList());
            log("过滤后，剩下 " + (fileList != null ? fileList.size() : 0) + " 个文件。");
            request.setAttribute("fileList", fileList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
