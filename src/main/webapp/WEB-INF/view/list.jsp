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
    <style>
        .table {
            display: table;
        }
        form button {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            background-color: white;
            text-decoration: underline;
        }
    </style>
</head>
<body style="padding: 0 10%">
    <h2><a href="/board/list">자유게시판 - 목록</a></h2>
    <nav>
        <form id="searchForm" action="/board/list" method="get">
            <input type="date" name="start">
            <input type="date" name="end">
            <select name="category">
                <option value="">전체 카테고리</option>
                <option value="1">자유</option>
                <option value="2">일상</option>
                <option value="3">음악</option>
                <option value="4">영화</option>
                <option value="5">여행</option>
            </select>
            <input type="text" name="keyword">
            <button type="submit" id="searchBtn">검색</button>
        </form>
    </nav>
    <div class="table">
        <div style="display:table-row">
            <p style="display: table-cell">카테고리</p>
            <p style="display: table-cell"></p>
            <p style="display: table-cell">제목</p>
            <p style="display: table-cell">작성자</p>
            <p style="display: table-cell">조회수</p>
            <p style="display: table-cell">등록일시</p>
            <p style="display: table-cell">수정일시</p>
        </div>
        <c:forEach var="article" items="${articles}">
            <div style="display: table-row">
                <div style="display: table-cell">${article.name}</div>
                <p style="display: table-cell"></p>
                <form action="/board/view" method="post"  style="display: table-cell">
                    <input type="text" value="${article.id}" name="id" hidden="hidden" />
                    <button>${article.title}</button>
                </form>
                <div style="display: table-cell">${article.author}</div>
                <div style="display: table-cell">${article.viewCount}</div>
                <div style="display: table-cell">${article.postDate}</div>
                <div style="display: table-cell">${article.editDate}</div>
            </div>
        </c:forEach>
    </div>
    <button type="button"><a href="${pageContext.request.contextPath}/board/write">등록</a></button>
    <div style="text-align: center">
        <c:forEach var="i" begin="1" end="${page.lastPage}">
            <span><a href="/board/list?pageNum=${i}">${i}</a></span>
        </c:forEach>
    </div>
</body>
<script>

</script>
</html>
