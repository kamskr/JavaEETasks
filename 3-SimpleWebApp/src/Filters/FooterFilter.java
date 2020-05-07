package Filters;

import Wrapper.ResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "FooterFilter", urlPatterns = "*.jsp")
public class FooterFilter implements Filter {

    private final String FOOTER = "<br><h2>FOOTER</h2>" +
            "<div id='date'></div>" +
            "</body>\n" +
            "</html>\n";

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ResponseWrapper responseWrapper = new ResponseWrapper(resp);
        chain.doFilter(req, responseWrapper);
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(responseWrapper.content());
        printWriter.write(FOOTER);
        printWriter.close();
    }


    public void init(FilterConfig config) throws ServletException {
        System.out.println("Init");
    }

    public void destroy() {
        System.out.println("Destroy");
    }
}
