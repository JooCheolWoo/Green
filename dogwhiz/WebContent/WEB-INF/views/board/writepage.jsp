<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>

    <script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/super-build/ckeditor.js"></script>
    <!-- 폰트 -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@300&display=swap" rel="stylesheet">

    <!-- Fontawesome 아이콘 -->
    <script src="https://kit.fontawesome.com/8382ce3f5f.js" crossorigin="anonymous"></script>

    <!-- stylesheets -->
    <link rel="stylesheet" href="./css/writepage.css?after">

    <!-- js -->
    <script src="./js/writepage.js?after" defer></script>

</head>
<body>
<section class="mainpage">
    <jsp:include page="../hf/header.jsp"/>
    <div id="container">
        <div class="writearea">
            <form:form id="post-form" modelAttribute="" method="post" action="">
                <div class="category">
                    <label for="category">말머리</label>
                    <select name="category" id="category">
                        <option value="공지">공지</option>
                        <option value="커뮤니티">커뮤니티</option>
                        <option value="개과사전">개과사전</option>
                        <option value="이벤트">이벤트</option>
                        <option value="피드백">피드백</option>
                    </select>
				    <div class="important" style="display: none;">
				        <input type="checkbox" id="important-checkbox" name="important" value="true">
				        <label for="important-checkbox">필독</label>
				    </div>         
	                <div class="subCategory" style="display: none; margin-left: 10px;">
	                    <select name="subCategory" id="subCategory">
	                        <option value="일상">일상</option>
	                        <option value="소식">소식</option>
	                        <option value="자랑">자랑</option>
	                        <option value="질문">질문</option>
	                    </select>
	                </div>				             
                </div>
                <div class="title">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="title" required>
                </div>
                <div id="editor" style=" display: none;"></div>
                <textarea id="content" name="content" style="display: none;"></textarea>
                <div class="btns">
                    <button type="submit" class="submitBtn" onclick="submitForm()">작성완료</button>
                    <button type="button" class="cancelBtn">작성취소</button>
                </div>
            </form:form>
        </div>
        <jsp:include page="../hf/footer.jsp"/>
    </div>
</section>
</body>
</html>