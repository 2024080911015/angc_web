<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入JSTL核心标签库，这是使用 c:forEach 的前提 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>樱吹雪 - 文件小屋</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=M+PLUS+Rounded+1c:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<div class="main-container">
    <h1>樱色の文件小屋</h1>
    <p class="subtitle">请选择想要上传的图片或视频</p>

    <form id="uploadForm" action="uploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" id="fileInput" name="files" multiple accept="image/*,video/*">
        <label for="fileInput" class="upload-button">🌸 选择文件</label>
        <div id="file-name-display" class="file-name-display">未选择任何文件</div>
        <button type="submit" class="submit-button">开始上传</button>
    </form>

    <%-- ==================== 新增的文件列表展示区 ==================== --%>
    <%-- 使用 c:if 判断 fileList 是否不为空且有内容 --%>
    <c:if test="${not empty fileList}">
        <div class="file-list-container">
            <h2>已上传的文件</h2>
            <ul class="file-list">
                    <%-- 使用 c:forEach 遍历从Servlet传来的 fileList --%>
                <c:forEach var="file" items="${fileList}">
                    <li class="file-item">
                        <span class="file-name">${file.name}</span>
                            <%-- 下载链接，指向我们新创建的 DownloadServlet --%>
                        <a href="download?file=${file.name}" class="download-button">下载</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <%-- ==================== 文件列表展示区结束 ==================== --%>

</div>

<div id="sakura-container"></div>

<script src="js/sakura.js"></script>
<script src="js/main.js"></script>
</body>
</html>