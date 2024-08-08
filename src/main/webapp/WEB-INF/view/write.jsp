<%--
  Created by IntelliJ IDEA.
  User: user-jh
  Date: 2024-08-07
  Time: 오후 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>게시판 - 등록</h2>
    <form method="post" action="/board/write" enctype="multipart/form-data">
        <label>
            카테고리
            <select name="category">
                <option value="1">자유</option>
                <option value="2">일상</option>
                <option value="3">음악</option>
                <option value="4">영화</option>
                <option value="5">여행</option>
            </select>
        </label>
        <label>
            작성자
            <input type="text" name="author"/>
        </label>
        <label>
            비밀번호
            <input type="password" name="password"><input type="text">
        </label>
        <label>
            제목
            <input type="text" name="title">
        </label>
        <label>
            내용
            <input type="text" name="content">
        </label>
        <label>
            파일 첨부
            <input type="file" name="files">
        </label>
        <button type="button">취소</button>
        <button type="submit">저장</button>
    </form>
</body>
<script>
</script>
</html>
