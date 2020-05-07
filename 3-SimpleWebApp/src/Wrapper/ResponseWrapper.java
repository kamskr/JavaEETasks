package Wrapper;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class ResponseWrapper extends HttpServletResponseWrapper {

    public final StringWriter writer = new StringWriter();

    public ResponseWrapper(HttpServletResponse response){
        super(response);
    }

    public ResponseWrapper(ServletResponse response) {
        this((HttpServletResponse)response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(writer);
    }

    public ServletOutputStream getOutputStream() throws IOException {
        throw new IllegalStateException("not allowed");
    }

    public String content() {
        return writer.toString();
    }
}
