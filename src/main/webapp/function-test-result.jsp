<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="filterForm" action="FunctionTestServlet">
		<input type="checkbox" name="color" value="red" onchange="applyFilter()">
		<input type="checkbox" name="color" value="blue" onchange="applyFilter()">
		<input type="checkbox" name="color" value="yellow" onchange="applyFilter()">
		<input type="checkbox" name="color" value="black" onchange="applyFilter()">
		<input type="checkbox" name="color" value="white" onchange="applyFilter()">
		<button type="submit">Submit</button>
	</form>
</body>
</html>