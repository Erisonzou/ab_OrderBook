<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.example.demo.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> List of Current Shoe Products in Store</h2>
<%List<Ask> s = (List<Ask>)request.getAttribute("stocklist"); %>
<table border = "1">
<tr><th>Ticker</th><th>Bid</th><th>Ask</th></tr>
<%for(Ask ss:s){ %>
<tr><td><%=ss.getStock() %></td>
<td><%=ss.getQuantity()%></td>
<td><%=ss.getPrice()%></td>
</tr><%} %>
</table>
</body>
</html>