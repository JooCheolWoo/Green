<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <title>공지사항</title>

    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">

    <!-- Fontawesome 아이콘 -->
    <script src="https://kit.fontawesome.com/8382ce3f5f.js" crossorigin="anonymous"></script>

    <!-- stylesheets -->
    <link rel="stylesheet" href="./css/announcement.css?after">

</head>

<body>
    <section class="mainpage">
	<jsp:include page="../hf/header.jsp"/>
        <div id="container">
            <div>
                <div class="sub_top">
                    <div class="sub_top_left">
                        <a href="javascript:history.back()" title="이전"><i class="fa-solid fa-chevron-left"></i></a>
                    </div>
                    <h2>
                        <a href="post?category=${category}">${category}</a>
                    </h2>
                    <div class="sub_top_right">
                        <a href="writepage">글쓰기</a>
                    </div>
                </div>
                <div class="post_search">
                    <div class="search_input">
                        <input type="search" class="post_search_txt" value placeholder="검색 단어를 입력해 주세요.">
                    </div>
                    <div class="search_btn">
                        <button type="button" class="post_search_btn">검 색</button>
                    </div>
                </div>
				 <div class="board_list">
					<c:set var="now" value="<%=java.time.LocalDateTime.now()%>" />
					<c:forEach items="${post}" var="post" begin="${(page) * 10}" end="${(page) * 10 + 9}">
						<div class="post_div">
							<div class="post_left">
								<div class="post_top">
									<span class="special">${post.important ? '[필독]' : ''}</span>
									<span>[${post.subCategory == null ? post.category : post.subCategory}]</span>
									<span>
										<c:choose>
							                <c:when test="${fn:length(post.title) > 15}">
							                    <a href="/dogwhiz/postview?no=${post.no}">${fn:substring(post.title, 0, 15)}...</a>
							                </c:when>
							                <c:otherwise>
							                    <a href="/dogwhiz/postview?no=${post.no}">${post.title}</a>
							                </c:otherwise>
						            	</c:choose>
									</span>
								</div>
								<div class="post_middle">
									<span class="nickname">${post.writer}</span>
									<span>
						            <c:choose>
						                <c:when test="${post.createdAt.toLocalDate() == now.toLocalDate()}">
						                    <span class="createdAt" style="font-size: 14px">${post.createdAt.toLocalTime()}</span>
						                </c:when>
						                <c:otherwise>
						                    <span class="createdAt" style="font-size: 14px">${post.createdAt.toLocalDate()} ${post.createdAt.toLocalTime()}</span>
						                </c:otherwise>
						            </c:choose>										
									</span>
								</div>
								<div class="post_bottom">
									<i class="fa-regular fa-heart"></i> <span>0</span>
									<i class="fa-regular fa-comment-dots"></i> <span>0</span>
									<i class="fa-regular fa-eye"></i> <span>${post.viewCount}</span>									
								</div>
							</div>
							<div class="post_image">
								<c:if test="${fn:contains(post.content, '<img')}">
								  <c:set var="firstImage" value="${fn:substringAfter(post.content, '<img')}"/>
								  <c:set var="firstImage" value="${fn:substringBefore(firstImage, '>')}"/>
								  <img ${firstImage}>
								</c:if>			
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="pagination">
					<c:choose>
					    <c:when test="${currentPage > 1}">
					        <a href="?page=${currentPage - 1}">이전</a>
					    </c:when>
					    <c:otherwise>
					    	<a>이전</a>
					    </c:otherwise>
				    </c:choose>
					<span>${currentPage}/${pageCount}</span>
					<c:choose>
					    <c:when test="${currentPage < pageCount}">
					        <a href="?page=${currentPage + 1}">다음</a>
					    </c:when>
						<c:otherwise>
							<a>다음</a>
						</c:otherwise>
					</c:choose>
				</div>	
            </div>
			<jsp:include page="../hf/footer.jsp"/>
        </div>
    </section>
</body>

</html>