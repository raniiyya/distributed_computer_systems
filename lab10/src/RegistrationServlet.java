import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class RegistrationServlet extends HttpServlet {
    private Map<String, String> users = new HashMap<>();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (users.containsKey(username)) {
            request.setAttribute("message", "User already exists!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            users.put(username, password);
            request.setAttribute("message", "Registration successful! Please log in.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
