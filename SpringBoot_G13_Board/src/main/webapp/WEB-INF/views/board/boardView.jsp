<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/board.css">
    <script>
        function replyCheck() {
            if (document.reply.content.value == "") {
                alert("댓글 내용을 입력하세요");
                document.reply.content.focus();
                return false;
            }
            return true;
        }

        function deleteBoard(pass, num) {
            let inputPass = prompt("비밀번호를 입력하세요", "");
            if (pass != inputPass) {
                alert("비밀번호가 일치하지 않습니다")
                return;
            } else {
                location.href = "deleteBoard?num=" + num;
            }
        }
    </script>
</head>
<body>
<div id="main_container">
    <h2>게시글 상세 보기</h2>
    <div class="board">
        <div class="field">
            <div class="label">작성자</div>
            <div class="text">${board.userid}</div>
        </div>
        <div class="field">
            <div class="label">이메일</div>
            <div class="text">${board.email}</div>
        </div>
        <div class="field">
            <div class="label">조회수</div>
            <div class="text">${board.readcount}</div>
        </div>
        <div class="field">
            <div class="label">작성일</div>
            <div class="text"><fmt:formatDate value="${board.writedate}"/></div>
        </div>
        <div class="field">
            <div class="label">제목</div>
            <div class="text">${board.title}</div>
        </div>
        <div class="field" style="margin-bottom : 15px; ">
            <div class="label">내용</div>
            <div class="text" style="font-size:140%;flex:1.5;">
                <pre>${board.content}</pre>
            </div>
            <div class="label" style="flex:0.5;">이미지</div>
            <div class="text" style="flex:2;">
                <c:choose>
                    <c:when test="${empty  board.savefilename}">
                        <img src="/upload/nofile.jpg" width="250"/>
                    </c:when>
                    <c:otherwise>
                        <img src="/upload/${board.savefilename}" width="350"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="login-button">
            <input type="button" class="btn-login" value="수정"
                   onClick="location.href='updateBoardForm?num=${board.num}'"/>
            <input type="button" class="btn-login" value="삭제"
                   onClick="deleteBoard('${board.pass}', '${board.num}')"/>
            <input type="button" class="btn-login" value="목록"
                   onClick="location.href='boardList'"/>
        </div>
    </div>
    <div class="reply">
        <div class="reply_row">
            <div class="reply_col reply_title">작성자</div>
            <div class="reply_col reply_title">작성일시</div>
            <div class="reply_col reply_title" style="text-align:center">댓글</div>
            <div class="reply_col reply_title">작성/삭제</div>
        </div>
        <form action="insertReply" name="reply" style="margin-bottom: 5px" method="post">
            <input type="hidden" name="userid" value="${loginUser.userid}"/>
            <input type="hidden" name="boardnum" value="${board.num}"/>
            <div class="reply_row">
                <div class="reply_col">${loginUser.userid}</div>
                <div class="reply_col">
                    <c:set var="now" value="<%=new java.util.Date()%>"/>
                    <fmt:formatDate value="${now}" pattern="MM/dd hh:mm"/>
                </div>
                <div class="reply_col">
                    <input type="text" name="content" size="75">
                </div>
                <div class="reply_col">
                    <input type="submit" value="답글 작성" onClick="return replyCheck();">
                </div>
            </div>
        </form>

        <c:forEach items="${replyList}" var="reply">
            <div class="reply_row">
                <div class="reply_col">${reply.userid}</div>
                <div class="reply_col">
                    <fmt:formatDate value="${reply.writedate}" pattern="MM/dd hh:mm"/>
                </div>
                <div class="reply_col">${reply.content}</div>
                <div class="reply_col">
                    <c:if test="${reply.userid == loginUser.userid}">
                        <input type="button" value="삭제"
                               onClick="location.href='deleteReply?replynum=${reply.replynum}&boardnum=${reply.boardnum}'"/>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
