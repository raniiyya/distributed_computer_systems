<%@ page import="javax.servlet.http.*, java.util.*" %>
<%@ page language="java" session="true" %>
<%
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("username") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<%
    ExchangeCalculatorBean calc = new ExchangeCalculatorBean();
    calc.setRate1(Double.parseDouble(request.getParameter("rate1")));
    calc.setRate2(Double.parseDouble(request.getParameter("rate2")));
    calc.setRate3(Double.parseDouble(request.getParameter("rate3")));

    request.getServletContext().setAttribute("exchangeBean", calc);
%>
<html>
<body>
    <h2>Currency Exchange Calculator</h2>
    <form action="exchange.jsp" method="post">
        Amount: <input type="text" name="amount" required><br>
        Currency: 
        <select name="currency">
            <option value="1">Currency 1</option>
            <option value="2">Currency 2</option>
            <option value="3">Currency 3</option>
        </select><br>
        <input type="submit" value="Calculate">
    </form>
    <% 
        if (request.getMethod().equals("POST")) {
            double amount = Double.parseDouble(request.getParameter("amount"));
            int currency = Integer.parseInt(request.getParameter("currency"));
            double result = calc.calculate(amount, currency);
    %>
        <p>Result: <%= result %></p>
    <% } %>
</body>
</html>
