document.addEventListener('DOMContentLoaded', () => {
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
});