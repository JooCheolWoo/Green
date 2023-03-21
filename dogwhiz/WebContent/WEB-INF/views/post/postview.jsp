<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>메인페이지</title>

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">

    <!-- Fontawesome 아이콘 -->
    <script src="https://kit.fontawesome.com/8382ce3f5f.js" crossorigin="anonymous"></script>

    <!-- stylesheets -->
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="./css/announcement.css?after">
    <link rel="stylesheet" href="./css/postview.css?after">

    <!-- js -->
    <script src="./js/main.js" defer></script>

</head>

<body>
    <section class="mainpage">
		<jsp:include page="../hf/header.jsp"/>
        <div id="container">
            <div class="main">
                <div class="sub_top">
                    <div class="sub_top_left">
                        <a href="javascript:history.back()" title="이전"><i class="fa-solid fa-chevron-left"></i></a>
                    </div>
                    <h2>
                       	<a href="post?category=${post.category}">${post.category}</a>
                    </h2>
                    <div class="sub_top_right">
                        <a href="writepage">글쓰기</a>
                    </div>
                </div>

				<div class="board_list">
					<div id="content">
						<h2>${post.important ? '[필독] ' : ''} [${post.category}] ${post.subCategory != null ? [post.subCategory] : ''} ${post.title}</h2>
						<p>글쓴이: ${post.writer} | 작성날짜: ${post.createdAt.toLocalDate()} ${post.createdAt.toLocalTime()} | 조회수: ${post.viewCount + 1} | 추천수: 0</p>
						<hr>
						<p>${post.content}</p>
					</div>
				</div>
            </div>
			<jsp:include page="../hf/footer.jsp"/>
        </div>
    </section>
</body>

</html>