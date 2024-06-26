<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<table width="500" cellpadding="0" cellspacing="0" border="1">
    <tr align="center">
        <td>번호</td>
        <td>작성자</td>
        <td>제목</td>
        <td>삭제</td>
    </tr>
    <c:forEach items="${list}" var="dto">
        <tr align="center">
            <td>${dto.id}</td>
            <td>${dto.writer}</td>
            <td>
                <a href="view?id=${dto.id}">${dto.title}</a>
            </td>
            <td>
                <a href="delete?id=${dto.id}">삭제</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p><a href="writeForm">글작성</a></p>
</body>
</html>