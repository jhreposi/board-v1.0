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
    <form method="post" id="saveForm" action="/board/write" enctype="multipart/form-data">
        <label>
            카테고리
            <select name="category" id="category">
                <option value=0>카테고리 선택</option>
                <option value="1">자유</option>
                <option value="2">일상</option>
                <option value="3">음악</option>
                <option value="4">영화</option>
                <option value="5">여행</option>
            </select>
        </label>
        <label>
            작성자
            <input type="text" name="author" id="author"/>
        </label>
        <label>
            비밀번호
            <input type="password" name="password" id="pass" placeholder="4이상 16미만,영문/특수/숫자포함">
            <input type="password" id="passCheck">
        </label>
        <label>
            제목
            <input type="text" name="title" id="title">
        </label>
        <label>
            내용
            <input type="text" name="content" id="content">
        </label>
        <label>
            파일 첨부
            <input type="file" name="files" id="file">
        </label>
        <button type="button" onclick="location.href='/board/list'">취소</button>
<%--        <button type="button" id="saveBtn" onclick="serverCheck()">저장</button>--%>
        <button type="submit" id="saveBtn" >저장</button>
    </form>
</body>
<script>
    const saveForm = document.querySelector('#saveForm');
    const category = document.querySelector('#category');
    const author = document.querySelector('#author');
    const title = document.querySelector('#title');
    const content = document.querySelector('#content');
    const pass = document.querySelector('#pass');
    const passCheck = document.querySelector('#passCheck')
    const file = document.querySelector('#file');

    document.querySelector('#saveBtn').addEventListener('click',function () {
        if (category.value === '0') {
            alert('카테고리 선택은 필수 입니다')
            return;
        }
        if (author.value.length < 3 || author.value.length > 4) {
            alert('작성자는 3글자 이상 5글자미만 가능합니다')
            return;
        }
        if (pass.value !== passCheck.value) {
            alert('비밀번호가 일치하지 않습니다')
            return;
        }
        if (!validatePassword(pass.value)) {
            alert('비밀번호 규칙을 확인해주세요')
            return;
        }
        if (lengthCheck(title, 4, 100)) {
            alert('제목을4글자이상 100글자 미만으로 입력해주세요')
            return;
        }
        if (lengthCheck(content, 4, 2000)) {
            alert('내용을4글자이상 2000글자 미만으로 입력해주세요')
            return;
        }
    })

    function validatePassword(password) {
        const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{4,15}$/;
        return passwordPattern.test(password);
    }
    function lengthCheck(target, up, down) {
        return target.value.length < up || target.value.length > down;
    }

    function serverCheck() {
        const formData = new FormData(document.getElementById('saveForm'));
        console.log(formData);
        const param = {}
        formData.forEach(function (value, key) {
            param[key] = value;
            console.log(param);
        })

        fetch('/board/vaildation')
    }
</script>
</html>
