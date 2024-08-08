<%--
  Created by IntelliJ IDEA.
  User: user-jh
  Date: 2024-08-08
  Time: 오후 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글</title>
</head>
<body>
    <div>
        <div class="info">
            <div>${article.author}</div>
            <div>${article.postDate}</div>
            <div>${article.editDate}</div>
        </div>
        <div class="title">
            <div>${article.name}</div>
            <div>${article.title}</div>
            <div>${article.viewCount}</div>
        </div>
        <div>${article.content}</div>
        <a href="#">${file.originalName}</a>

        <div class="comment-container">
            <c:forEach var="comment" items="${comments}">
                <p>${comment.postDate}</p>
                <p>${comment.comment}</p>
            </c:forEach>
            <label>
                <input type="text" placeholder="댓글을 입력해 주세요."/>
            </label>
            <button type="button" class="commentBtn">등록</button>
        </div>
    </div>
    <hr>
    <button>목록</button>
    <button>수정</button>
    <button>삭제</button>
</body>
</html>
