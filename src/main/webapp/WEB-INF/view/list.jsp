<%--
  Created by IntelliJ IDEA.
  User: user-jh
  Date: 2024-08-05
  Time: 오후 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>자유게시판 - 목록</h2>
    <div class="all-packages-container">
        <c:forEach var="article" items="${articles}">
            <div>${article.name}</div>
            <div><a href="${pageContext.request.contextPath}/board/view/${article.id}">${article.title}</a></div>
            <div>${article.author}</div>
            <div>${article.viewCount}</div>
            <div>${article.postDate}</div>
            <div>${article.editDate}</div>
        </c:forEach>
    </div>
    <button type="button"><a href="${pageContext.request.contextPath}/board/write">등록</a></button>
</body>
</html>
