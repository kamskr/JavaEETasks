package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "HeaderFilter", urlPatterns ="*.jsp")
public class HeaderFilter implements Filter {
    private static final String HEADER =
            "<html>\n" +
            "  <head>\n" +
            "    <title>Simple web app</title>\n" +
            "    <script src=\"https://code.jquery.com/jquery-1.10.2.js\"\n" +
            "            type=\"text/javascript\"></script>\n" +
            "<script type=\"text/javascript\" src=\"/scripts/index.js\">  </script>" +

            "  </head>\n" +
            "  <body> " +
                    "<h2>HEADER</h2><br>";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(HEADER);
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
