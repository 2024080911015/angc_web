document.addEventListener('DOMContentLoaded', () => {
    const body = document.body;

    function createPetal() {
        const petal = document.createElement('div');
        petal.classList.add('petal');

        const startX = Math.random() * window.innerWidth;
        const duration = Math.random() * 5 + 8; // 飘落时间 8-13秒
        const delay = Math.random() * 5; // 延迟 0-5秒
        const scale = Math.random() * 0.5 + 0.5; // 大小 0.5-1.0
        const initialRotation = Math.random() * 360;

        petal.style.left = `${startX}px`;
        petal.style.animationDuration = `${duration}s, ${Math.random() * 3 + 2}s`;
        petal.style.animationDelay = `${delay}s`;
        petal.style.transform = `scale(${scale}) rotate(${initialRotation}deg)`;

        const swayAnimationName = `sway${Math.floor(Math.random() * 3)}`;
        petal.style.animationName = `fall, ${swayAnimationName}`;

        body.appendChild(petal);

        setTimeout(() => {
            petal.remove();
        }, (duration + delay) * 1000);
    }

    function createSwayAnimations() {
        let styleSheet = document.createElement('style');
        styleSheet.textContent = `
            @keyframes sway0 { 0% { margin-left: 0px; } 50% { margin-left: 50px; } 100% { margin-left: -20px; } }
            @keyframes sway1 { 0% { margin-left: 0px; } 50% { margin-left: -60px; } 100% { margin-left: 30px; } }
            @keyframes sway2 { 0% { margin-left: 0px; } 50% { margin-left: 20px; } 100% { margin-left: -40px; } }
        `;
        document.head.appendChild(styleSheet);
    }

    createSwayAnimations();
    setInterval(createPetal, 400);
});