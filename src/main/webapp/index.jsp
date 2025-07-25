<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <c:if test="${not empty fileList}">
        <div class="file-list-container">
            <h2>文件预览画廊</h2>
            <div class="preview-grid">
                <c:forEach var="file" items="${fileList}">
                    <div class="preview-card">
                        <c:set var="filename" value="${file.name.toLowerCase()}" />
                        <c:set var="fileUrl" value="download?file=${file.name}&disposition=inline" />

                        <c:choose>
                            <c:when test="${filename.endsWith('.jpg') or filename.endsWith('.jpeg') or filename.endsWith('.png') or filename.endsWith('.gif') or filename.endsWith('.webp')}">
                                <img src="${fileUrl}" alt="${file.name}" class="preview-media">
                            </c:when>
                            <c:when test="${filename.endsWith('.mp4') or filename.endsWith('.webm') or filename.endsWith('.ogg')}">
                                <video src="${fileUrl}" muted loop class="preview-media"></video>
                                <div class="video-overlay">▶</div>
                            </c:when>
                            <c:when test="${filename.endsWith('.pdf')}">
                                <div class="preview-placeholder">
                                    <span class="placeholder-icon">📄</span>
                                    <span class="placeholder-text">PDF 文件</span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="preview-placeholder">
                                    <span class="placeholder-icon">📁</span>
                                    <span class="placeholder-text">无法预览</span>
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <div class="card-overlay">
                            <p class="card-filename">${file.name}</p>
                            <div class="card-actions">
                                <a href="download?file=${file.name}" class="card-action-btn" title="下载">📥</a>
                                <span class="card-action-btn preview-trigger" title="放大预览" data-filename="${file.name}">🔍</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>

<div id="preview-modal" class="modal-overlay">
    <span class="modal-close">&times;</span>
    <div class="modal-content">
        <div id="preview-content-area">
        </div>
    </div>
</div>

<script src="js/sakura.js"></script>
<script src="js/main.js"></script>
</body>
</html>