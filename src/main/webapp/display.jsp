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
.center{
  margin-left: auto;
  margin-right: auto;
  width:60% ;
  padding: 5px;
  border: 1px solid #ddd;}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>
<%="Welcome "+ request.getAttribute("userid") %>
</h1>
<br>
<div>
<form action = "AskAndBid">
Stock <input type = "text" name = "stockname"><br>
<input type = "submit" value = "Ask And Bid Table">
</form>

</div>
<br>

<div>

<h2> List of Stocks</h2>
<%List<Stocks> s = (List<Stocks>)request.getAttribute("stocklist"); %>

<table class = "center" >
<tr><th>Ticker</th><th>Highest Bid</th><th>Lowest Ask</th><th>Volume</th></tr>
<%for(Stocks ss:s){ %>
<tr><td><%=ss.getTicker() %></td>
<td><%=ss.getLatestbid()%></td>
<td><%=ss.getLatestask()%></td>
<td><%=ss.getVolume()%></td>
</tr><%} %>
</table>

</div>


</body>
</html>