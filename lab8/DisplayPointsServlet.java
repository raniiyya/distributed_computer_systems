import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DisplayPoints")
public class DisplayPointsServlet extends HttpServlet {
    private CalculatorService service = new CalculatorService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int n = Integer.parseInt(request.getParameter("n"));
        double start = Double.parseDouble(request.getParameter("start"));
        double end = Double.parseDouble(request.getParameter("end"));
        double stdDev = Double.parseDouble(request.getParameter("stdDev"));

        double[] sequence = service.generateSequence(n, start, end);
        double[] disturbed = service.disturbSequence(sequence, stdDev);

        response.getWriter().println("<html><body>");
        response.getWriter().println("<table border='1'><tr><th>Index</th><th>Value</th><th>Disturbed</th></tr>");
        for (int i = 0; i < sequence.length; i++) {
            response.getWriter().println("<tr><td>" + i + "</td><td>" + sequence[i] + "</td><td>" + disturbed[i] + "</td></tr>");
        }
        response.getWriter().println("</table></body></html>");
    }
}
