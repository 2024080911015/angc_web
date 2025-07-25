# 樱色の文件小屋 (Sakura File House)

一个基于Java Web技术栈开发的文件上传下载管理系统，具有优雅的樱花主题UI设计（本项目主要是为了学习设计，目前不具有太大的实用价值）。

## 项目简介

本项目是一个功能完整的文件管理Web应用，支持文件上传、下载、预览等功能。界面采用樱花主题设计，具有动态樱花飘落效果，为用户提供美观且实用的文件管理体验。

## 技术栈

### 后端技术
- **Java 23** - 核心开发语言
- **Jakarta Servlet 6.1.0** - Web服务器端开发
- **Jakarta JSTL 3.0.0** - JSP标准标签库
- **Jersey 4.0.0-M1** - RESTful Web服务框架
- **Hibernate 7.0.0.Beta1** - ORM框架
- **Maven** - 项目构建和依赖管理

### 前端技术
- **JSP** - 动态网页技术
- **HTML5/CSS3** - 页面结构和样式
- **JavaScript** - 交互逻辑
- **Google Fonts** - 字体资源

## 主要功能

### 🌸 文件上传
- 支持多文件同时上传
- 支持图片和视频文件格式
- 实时显示选择的文件名
- 上传进度反馈

### 📁 文件管理
- 文件列表展示
- 文件预览功能
- 支持图片、视频、PDF等多种格式预览
- 文件下载功能

### 🎨 用户界面
- 樱花主题设计
- 响应式布局
- 动态樱花飘落动画
- 毛玻璃效果背景
- 优雅的卡片式文件展示

## 项目结构

```
angc_web-master/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/demo3/
│       │       ├── IndexServlet.java      # 主页控制器
│       │       ├── FileUploadServlet.java # 文件上传处理
│       │       ├── DownLoadServlet.java   # 文件下载处理
│       │       ├── FileServlet.java       # 文件服务处理
│       │       └── HelloServlet.java      # 基础Servlet
│       ├── resources/
│       │   └── META-INF/
│       │       └── persistence.xml        # JPA配置
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml               # Web应用配置
│           ├── css/
│           │   └── style.css             # 样式文件
│           ├── js/
│           │   ├── main.js               # 主要JavaScript逻辑
│           │   └── sakura.js             # 樱花动画效果
│           ├── images/
│           │   └── bg.jpg                # 背景图片
│           └── index.jsp                 # 主页面
├── target/                               # 编译输出目录
├── pom.xml                              # Maven配置文件
└── README.md                            # 项目说明文档
```

## 快速开始

### 环境要求
- Java 23 或更高版本
- Maven 3.6 或更高版本
- Tomcat 10 或其他支持Jakarta EE的Web服务器

### 安装步骤

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd angc_web-master
   ```

2. **配置上传目录**
   
   在运行项目前，需要确保系统中存在文件上传目录：
   ```
   D:/uploads2
   ```
   
   或者修改以下文件中的路径配置：
   - `IndexServlet.java` (第18行)
   - `FileUploadServlet.java` (第35行)
   - `DownLoadServlet.java` (第17行)

3. **编译项目**
   ```bash
   mvn clean compile
   ```

4. **打包项目**
   ```bash
   mvn package
   ```

5. **部署到Web服务器**
   
   将生成的 `target/demo2-1.0-SNAPSHOT.war` 文件部署到Tomcat等Web服务器中。

6. **访问应用**
   
   在浏览器中访问：`http://localhost:8080/demo2-1.0-SNAPSHOT/`

## 配置说明

### 文件上传配置
- 默认上传目录：`D:/uploads2`
- 支持的文件类型：图片（jpg, jpeg, png, gif, webp）、视频（mp4, webm, ogg）、PDF等
- 文件大小限制：由服务器配置决定

### 数据库配置
项目包含Hibernate配置，如需使用数据库功能，请在 `persistence.xml` 中配置数据库连接信息。

## 主要特性

### 🔒 安全特性
- 文件类型验证
- 路径安全检查
- 错误处理机制

### 🎯 用户体验
- 拖拽上传支持
- 实时预览
- 响应式设计
- 优雅的加载动画

### 🚀 性能优化
- 文件流式传输
- 前端资源压缩
- 缓存策略

## 开发说明

### 添加新的文件类型支持
1. 在 `index.jsp` 中添加文件类型判断逻辑
2. 在CSS中添加对应的预览样式
3. 更新文件上传的accept属性

### 自定义样式
- 主要样式文件：`src/main/webapp/css/style.css`
- 樱花动画：`src/main/webapp/js/sakura.js`
- 背景图片：`src/main/webapp/images/bg.jpg`

## 故障排除

### 常见问题

1. **文件上传失败**
   - 检查上传目录是否存在且有写入权限
   - 确认文件大小未超过服务器限制

2. **文件预览不显示**
   - 检查文件路径配置
   - 确认文件格式支持

3. **样式显示异常**
   - 检查CSS文件路径
   - 确认字体资源加载正常

## 贡献指南

欢迎提交Issue和Pull Request来改进项目！

### 开发规范
- 遵循Java编码规范
- 保持代码注释完整
- 测试新功能后再提交

## 许可证

本项目采用MIT许可证，详情请查看LICENSE文件。

## 联系方式

如有问题或建议，请通过以下方式联系：
- 提交Issue
- 发送邮件

---

**享受樱花飘落的文件管理体验！** 🌸
