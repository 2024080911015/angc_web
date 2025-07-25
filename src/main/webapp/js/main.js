document.addEventListener('DOMContentLoaded', () => {
    // --- 文件上传交互逻辑 ---
    const fileInput = document.getElementById('fileInput');
    const fileNameDisplay = document.getElementById('file-name-display');

    if (fileInput) {
        fileInput.addEventListener('change', (event) => {
            const files = event.target.files;
            if (files.length === 0) {
                fileNameDisplay.textContent = '未选择任何文件';
            } else if (files.length === 1) {
                fileNameDisplay.textContent = `已选择: ${files[0].name}`;
            } else {
                fileNameDisplay.textContent = `已选择 ${files.length} 个文件`;
            }
        });
    }

    // --- 文件预览模态框逻辑 ---
    const modal = document.getElementById('preview-modal');
    const modalClose = document.querySelector('.modal-close');
    const previewContentArea = document.getElementById('preview-content-area');
    const previewTriggers = document.querySelectorAll('.preview-trigger');

    // 根据文件名后缀判断文件类型
    const getFileType = (filename) => {
        const extension = filename.split('.').pop().toLowerCase();
        if (['jpg', 'jpeg', 'png', 'gif', 'bmp', 'webp', 'svg'].includes(extension)) {
            return 'image';
        }
        if (['mp4', 'webm', 'ogg'].includes(extension)) {
            return 'video';
        }
        if (extension === 'pdf') {
            return 'pdf';
        }
        return 'unsupported';
    };

    previewTriggers.forEach(trigger => {
        trigger.addEventListener('click', (e) => {
            // 阻止事件冒泡，防止点击图标时触发卡片链接等
            e.stopPropagation();

            const filename = trigger.dataset.filename;
            const fileType = getFileType(filename);

            previewContentArea.innerHTML = ''; // 清空上一次的预览内容

            const fileUrl = `download?file=${encodeURIComponent(filename)}&disposition=inline`;
            let previewElement;

            switch (fileType) {
                case 'image':
                    previewElement = document.createElement('img');
                    previewElement.src = fileUrl;
                    previewElement.alt = `预览: ${filename}`;
                    break;
                case 'video':
                    previewElement = document.createElement('video');
                    previewElement.src = fileUrl;
                    previewElement.controls = true;
                    previewElement.autoplay = true;
                    break;
                case 'pdf':
                    previewElement = document.createElement('embed');
                    previewElement.src = fileUrl;
                    previewElement.type = 'application/pdf';
                    break;
                case 'unsupported':
                default:
                    previewElement = document.createElement('div');
                    previewElement.className = 'unsupported-preview';
                    previewElement.innerHTML = `<h3>无法预览此文件</h3><p>${filename}</p><p>请直接下载后查看。</p>`;
                    break;
            }

            if (previewElement) {
                previewContentArea.appendChild(previewElement);
            }

            modal.style.display = 'flex';
        });
    });

    // 关闭模态框的函数
    const closeModal = () => {
        modal.style.display = 'none';
        const media = previewContentArea.querySelector('video, audio');
        if (media) {
            media.pause();
            media.src = '';
        }
        previewContentArea.innerHTML = '';
    };

    modalClose.addEventListener('click', closeModal);
    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            closeModal();
        }
    });
});