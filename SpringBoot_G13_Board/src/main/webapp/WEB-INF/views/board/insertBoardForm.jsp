<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/board.css">
    <script>
        function selectImg() {
            let opt = "toolbar=no,menubar=no,resizable=no, width=450, height=200";
            window.open('selectimg', 'selectimg', opt);
        }
    </script>
</head>
<body>
<div id="main_container">
    <h2>게시글 등록</h2>
    <div class="board">
        <form class="insertBoard" method="post" name="insertBoard"
              action="insertBoard" enctype="multipart/form-data">
            <div class="field">
                <label>작성자</label>
                <input type="text" name="userid" value="${loginUser.userid}" readonly/>
            </div>
            <div class="field">
                <label>비밀번호</label>
                <input style="flex:2" type="password" name="pass"/>
                <div style="flex:2; margin-left:20px;">게시물 수정 삭제시 필요합니다.</div>
            </div>
            <div class="field">
                <label>이메일</label>
                <input type="text" name="email" value="${loginUser.email}" readonly/>
            </div>
            <div class="field">
                <label>제목</label>
                <input type="text" name="title" value="${dto.title}"/>
            </div>
            <div class="field">
                <label>내용</label>
                <textarea name="content" rows="10" cols="100">${dto.content}</textarea>
            </div>
            <div class="field">
                <label>이미지</label>
                <input type="button" value="파일 선택" onclick="selectImg()"/>
            </div>
            <c:choose>
                <c:when test="${empty dto.image}">
                    <div class="field">
                        <label>미리보기</label>
                        <div style="flex:4">
                            <img src="" id="previewimg" width="150" style="display: none">
                            <input type="hidden" name="savefilename">
                            <input type="hidden" name="image">
                            <div id="image"></div>
                            <div id="savefilename"></div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="field">
                        <label>미리보기</label>
                        <div style="flex:4">
                            <img src="/upload/${dto.savefilename}" id="previewimg" width="150">
                            <input type="hidden" name="savefilename" value="${dto.savefilename}">
                            <input type="hidden" name="image" value="${dto.image}">
                            <div id="image">${dto.image}</div>
                            <div id="savefilename">${dto.savefilename}</div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="field">
                <input type="submit" value="작성완료"/>
                <input type="button" value="취소" onclick="history.back()"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>