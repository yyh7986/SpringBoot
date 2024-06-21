<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<section>
    <%@ include file="sub_image_menu.jsp" %>
    <article>
        <form action="joinForm" name="contractFrm" method="post">
            <p>언제나 새로운 즐거움이 가득한 Shoes Shop의 회원가입 페이지 입니다.<br/>
                Shoes Shop의 회원가입은 무료이며, 회원님의 개인신상에 관한 정보는
                '정보통신망이용촉진 및 정보보호등에관한법률'에 의해 회원님의 동의없이
                제 3자에게 제공되지 않으며, 철저히 보호되고 있사오니 안심하고 이용하시기 바랍니다. </p>

            <div class="ta"><textarea rows="15">... 약관 전문 ...</textarea></div>

            <div class="rb">
                <input type="radio" name="okon">
                동의함 &nbsp; &nbsp; &nbsp;
                <input type="radio" name="okon" checked>
                동의안함
            </div>

            <div class="btn">
                <input type="button" value="Next" onClick="go_next();">
            </div>
        </form>
    </article>
    </div>
</section>
</body>
</html>
<%@ include file="../include/footer.jsp" %>