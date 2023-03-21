<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/header.css">
<script src="./js/header.js" defer></script>

</head>
<body>
	<header>
	     <div class="header_box">
	         <!-- 좌측 메뉴 -->
	         <ul class="left_menu">
	             <li>
	                 <a href="#" class="side_menu" alt="사이드메뉴" title="사이드메뉴">
	                     <i class="fa-solid fa-bars"></i>
	                 </a>
	             </li>
	         </ul>         
	         <!-- 로고 -->
	         <div class="m_logo">
	             <a href="main">
	                 <img src="./images/logo/logo.png" height="45px">
	             </a>
	         </div>
	         <!-- 우측 메뉴 -->
	         <ul class="right_menu">
	             <li>
	                 <a href="#" class="search" alt="검색" title="검색">
	                     <i class="fa-solid fa-magnifying-glass"></i>
	                 </a>
	             </li>
	         </ul>
	         <div class="category_scroll">
	             <ul>
	                 <li><a href="post?category=공지사항">공지사항</a></li>
	                 <li><a href="post?category=산책커뮤니티">산책커뮤니티</a></li>
	                 <li><a href="post?category=커뮤니티">커뮤니티</a></li>
	                 <li><a href="post?category=개과사전">개과사전</a></li>
	                 <li><a href="post?category=이벤트">이벤트</a></li>
	                 <li><a href="post?category=피드백">피드백</a></li>
	             </ul>
	         </div>
	     </div>
	 </header>
</body>
</html>