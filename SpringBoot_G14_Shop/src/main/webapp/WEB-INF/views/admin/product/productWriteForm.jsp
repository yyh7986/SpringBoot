<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="../header.jsp" %>
<%@ include file="../sub_menu.jsp" %>

<article style="display: flex; flex-direction: column">
    <form name="productWriteFrm" method="post" action="adminProductWrite">
        <h2> Product Write Form </h2>
        <div class="field">
            <label>상품분류</label>
            <div>
                <select name="kind" style="width:200px;height:25px;font-size:105%;">
                    <option value="">선택하세요</option>
                    <c:forEach items="${kindList}" var="kind" varStatus="status">
                        <option value="${status.count}">${kind}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="field">
            <label>상품명</label>
            <input type="text" name="name"/>
        </div>
        <div class="field">
            <label>원가</label>
            <input type="text" name="price1" style="margin-right:20px;" onKeyup="cals();"/>
            <label>판매가</label>
            <input type="text" name="price2" style="margin-right:20px;" onKeyup="cals();"/>
            <label>마진</label>
            <input type="text" name="price3" readonly/>
        </div>
        <div class="field">
            <label>상세설명</label>
            <textarea name="content" rows="8" style="flex:4;"></textarea>
        </div>
        <div class="field">
            <label>상품이미지</label>
<%--            <input type="file" name="image">--%>
            <div>
                <input type="hidden" name="image" id="image">
                <input type="hidden" id="savefilename">
                <div id="filename"></div>
            </div>
        </div>
        <div class="btn">
            <input type="submit" value="상품등록">
            <input type="button" value="목록으로"
                   onClick="location.href='adminProductList'">
        </div>
    </form>
    <div style="position: relative; border: 1px solid black; width: 500px; margin: 0 auto; top: -140px">
        <form name="frm" id="fileUploadForm" method="post" enctype="multipart/form-data">
            <input type="file" name="fileImage">
            <input type="button" id="myButton" value="추가">
        </form>
    </div>
</article>
<%@ include file="../footer.jsp" %>