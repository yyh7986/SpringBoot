<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<section>
		<div class="sub_img">
				<img src="/images/product/sub_img.jpg" />
		</div>
		<div class="sub_page" >
				<article style="flex-direction:column">
						<h2>${kind}</h2>
						<div class="kindproducts">
								<c:forEach items="${productKindList}" var="pvo">
										<div class="kinditem"  
								onClick="location.href='productDetail?pseq=${pvo.pseq}'">
												<img src="/product_images/${pvo.savefilename}" />
												<div>${pvo.name}</div>
												<div>${pvo.price2}</div>
										</div>
								</c:forEach>
						</div>
				</article>
		</div>
</section>

<%@ include file="../include/footer.jsp" %>