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
    </style>
</head>
<body>
    <h2>자유게시판 - 목록</h2>
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
</body>
<script>
    rows = document.querySelectorAll('#viewLink');
    rows.forEach(row => {
        row.addEventListener('click', function (){
            const id = this.getAttribute('data-id');
            console.log('click id ', id)
            const data = {'id' : id}

            fetch(`${pageContext.request.contextPath}/board/view`, {
                headers : {'Content-Type' : 'application/json'},
                method  : 'POST',
                body : JSON.stringify(data)
            }).then(response => {
                console.log(response)
            })
        });
    })
</script>
</html>
