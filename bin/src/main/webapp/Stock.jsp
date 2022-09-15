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
<%List<Stocks> s = (List<Stocks>)request.getAttribute("stocklist"); %>
<table border = "1">
<tr><th>Ticker</th><th>Open</th><th>Bid</th><th>Ask</th><th>Volume</th></tr>
<%for(Stocks ss:s){ %>
<tr><td><%=ss.getTicker() %></td>
<td><%=ss.getOpen()%></td>
<td><%=ss.getBid()  %></td>
<td><%=ss.getAsk()  %></td>
<td><%=ss.getVolume() %></td>
</tr><%} %>
</table>
</body>
</html>