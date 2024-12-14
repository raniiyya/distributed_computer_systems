<html>
<body>
    <h2>Register</h2>
    <form action="RegistrationServlet" method="post">
        Username: <input type="text" name="username" required><br>
        Password: <input type="password" name="password" required><br>
        <input type="submit" value="Register">
    </form>
    <p><%= request.getAttribute("message") %></p>
</body>
</html>
