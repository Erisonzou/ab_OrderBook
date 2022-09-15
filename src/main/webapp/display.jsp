<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.example.demo.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<style>
div {text-align: center;}
h1 {text-align: center;}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>
<%="Welcome "+ request.getAttribute("userid") %>
</h1>
<div>
<form action="Stocks">
<input type="submit" value="All Stocks">
</form>
</div>

<div>


</div>

</body>
</html>