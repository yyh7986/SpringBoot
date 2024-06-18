<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <script type="text/javascript">
        function idok(id){
            opener.document.join.userid.value = id;
            opener.document.join.reid.value = id;
            self.close();
        }
    </script>
    <style>
        body{
            display: flex;
            flex-direction: column;
            height: 100vh;
            align-items: center;
            padding: 20px;
        }
    </style>
</head>
<body>
<!-- 재검색창 -->
<form action="idcheck" style="margin:20px 0; ">
    아이디 : <input type="text" name="userid" value="${userid}"/><!-- value 에 확인된 아이디 표시 -->
    <input type="submit" value="중복체크"/>
</form>

<!-- 사용 가능/불가능 -->
<div>
    <c:choose>
        <c:when test="${result==1}">
            <script type="text/javascript">
                opener.document.join.userid.value = "";
                opener.document.join.reid.value = "";
            </script>
            ${userid}는 이미 사용 중인 아이디입니다.
        </c:when>
        <c:otherwise>
            ${userid}는 사용 가능한 아이디입니다.
            <input type="button" value="사용" onClick="idok('${userid}')"/>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
