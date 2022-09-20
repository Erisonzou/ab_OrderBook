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
<h2> Transaction List</h2>
<%List<BookOrder> s = (List<BookOrder>)request.getAttribute("BookOrderlist"); %>
<table border = "1">
<tr><th>User</th><th>Stock</th><th>Order Type</th><th>Quantity</th><th>Price</th><th>Order Status</th><th>Time</th></tr>
<%for(BookOrder ss:s){ %>
<tr><td><%=ss.getUser() %></td>
<td><%=ss.getStock()%></td>
<td><%=ss.getOrderType()%></td>
<td><%=ss.getQuantity()%></td>
<td><%=ss.getPrice()%></td>
<td><%=ss.getOrderStatus()%></td>
<td><%=ss.getTime()%></td>
</tr><%} %>
</table>

</body>
</html>