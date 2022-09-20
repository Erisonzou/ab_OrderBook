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
<tr><th>Ticker</th><th>Bid</th><th>Ask</th></tr>
<%for(Stocks ss:s){ %>
<tr><td><%=ss.getTicker() %></td>
<td><%=ss.getLatestbid()%></td>
<td><%=ss.getLatestask()%></td>
<td><%=ss.getVolume()%></td>
</tr><%} %>
</table>

<form action = "AskAndBid">
Stock <input type = "text" name = "stockname"><br>
<input type = "submit" value = "Ask And Bid Table">
</form>
</body>
</html>