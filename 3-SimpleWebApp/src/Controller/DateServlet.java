package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "DateServlet", urlPatterns = "/date")
public class DateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        response.setContentType("text/plain");
        PrintWriter output = response.getWriter();
        output.write(dtf.format(now));
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Date servlet destroy");
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Date Servlet init");
    }
}
