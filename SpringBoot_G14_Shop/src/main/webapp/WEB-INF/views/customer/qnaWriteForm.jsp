<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<section>
    <%@ include file="sub_image_menu.jsp" %>
    <article>
        <form action="qnaWrite" method="post" name="frm">
            <h2> Q & A Write</h2>
            <h2>${message}</h2>
            <div class="field" >
                <label>작성자</label>
                <input type="text" name="userid" value="${loginUser.userid}" readonly />
            </div>
            <div class="field" >
                <label>비공개로 작성</label>
                <input type="checkbox" name="secret" value="Y" onchange="enable()" />
                <input type="password" name="pass" size="15" disabled/>
            </div>
            <div class="field" >
                <label>제목</label><input type="text" name="subject"  >
            </div>
            <div class="field" >
                <label>질문내용</label>
                <textarea name="content" rows="10"  cols="85"></textarea>
            </div>
            <div class="btn" >
                <input type="submit" value="작성하기" />
            </div>
        </form>
    </article>
    </div>
</section>
<%@ include file="../include/footer.jsp" %>