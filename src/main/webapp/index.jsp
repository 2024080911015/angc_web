<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>樱吹雪 - 文件上传</title>
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

        <label for="fileInput" class="upload-button">
            🌸 选择文件
        </label>

        <div id="file-name-display" class="file-name-display">未选择任何文件</div>

        <button type="submit" class="submit-button">开始上传</button>
    </form>
</div>

<script src="js/sakura.js"></script>
<script src="js/main.js"></script>
</body>
</html>