import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    private Map<String, String> users = new HashMap<>();

    public void init() {
       
        users.put("testuser", "testpass");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("exchange.jsp");
        } else {
            request.setAttribute("message", "Invalid login credentials!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
