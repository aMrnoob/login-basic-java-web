<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User" %>
<%
    User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Welcome, <%= user.getFullName() %>!</h1>
    <p>Email: <%= user.getEmail() %></p>
    <p>Phone: <%= user.getPhone() %></p>
    <p>Address: <%= user.getAddress() %></p>
</body>
</html>
