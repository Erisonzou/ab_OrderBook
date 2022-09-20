<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ page import = "com.example.demo.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<style>
.div1{background-color: #ff355e; width: 50%; float:left;}
.div2{background-color: #00ff00; width: 50%; float: right;}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<form action ="orderApplication">
<input type="submit" value = "create a bid/ask">
</form>
<div class = "tables">
<div class = "div1">
<body>
<%List<Ask> s = (List<Ask>)request.getAttribute("asklist"); %>
<table border = "1">
<tr><th>Ticker</th><th>Ask</th><th>price</th></tr>
<%for(Ask ss:s){ %>
<tr><td><%=ss.getStock() %></td>
<td><%=ss.getQuantity()%></td>
<td><%=ss.getPrice()%></td>
</tr><%} %>
</table>
</div>

<div class = "div2">
<%List<Bid> ss = (List<Bid>)request.getAttribute("bidlist"); %>
<table border = "1">
<tr><th>Ticker</th><th>Bid</th><th>price</th></tr>
<%for(Bid sss:ss){ %>
<tr><td><%=sss.getStock() %></td>
<td><%=sss.getQuantity()%></td>
<td><%=sss.getPrice()%></td>
</tr><%} %>
</table>
</div>
</div>

</body>
</html>