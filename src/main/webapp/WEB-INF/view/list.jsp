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
<%--            <div><a href="#" id="viewLink" data-id="${article.id}">${article.title}</a></div>--%>
<%--            <div><a href="#" id="viewLink" data-id="${article.id}">${article.title}</a></div>--%>
            <form action="/board/view" method="post">
                <input type="text" value="${article.id}" name="id" hidden="hidden" />
                <button>${article.title}</button>
            </form>
            <div>${article.author}</div>
            <div>${article.viewCount}</div>
            <div>${article.postDate}</div>
            <div>${article.editDate}</div>
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
