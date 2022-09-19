<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1 {text-align:center;}
form {text-align:center;}
</style>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<h1><i>New Ask</i></h1>

<form action="AddAsk">

Stock Name<input type="text" name="stock"><br>
Quantity<input type="number" name="quantity"><br>
Price<input type="text" name="pricePoint"><br>
User ID<input type="text" name="userid"><br>


<input type="submit" value="Submit Ask">

</form>
</body>
</html>