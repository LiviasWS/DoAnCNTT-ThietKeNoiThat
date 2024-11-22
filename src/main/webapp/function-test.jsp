<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="js/function-test.js" ></script>
	<script type="text/javascript">
	    const contextPath = "${pageContext.request.contextPath}";
	</script>
</head>
<body>
	<form id="filterForm">
		<ul>
			<c:forEach var = "color" items="${colors}">
				<c:choose>
					<c:when test="${fn:contains(selectedColors, color.id)}">
						<input type="checkbox" name="color" value="${color.id}" onchange = "colorFilter()" checked>${color.name}<br>
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="color" value="${color.id}" onchange = "colorFilter()">${color.name}<br>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
		<h2>${count} option checked</h2>
	</form>
</body>
</html>