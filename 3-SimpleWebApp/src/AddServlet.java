import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "AddServlet", urlPatterns = "/add")
public class AddServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performAction(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        performAction(request,response);
    }

    private void performAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BigInteger value1 = value(request, "value1");
        BigInteger value2 = value(request, "value2");

//        if (value1 == null || value2 == null){
//            PrintWriter output = response.getWriter();
//            output.print("Wrong input!");
//            output.close();
//        }
        BigInteger sum = value1.add(value2);
        PrintWriter output = response.getWriter();
        output.print(value1 + " + " + value2 + " = " + sum);
        output.close();
    }
    private BigInteger value(HttpServletRequest request, String parameterName){
        String parameterInput = request.getParameter(parameterName);
        System.out.println(parameterName);
        System.out.println(parameterInput);
//        Matcher matcher = INT_REG.matcher(parameterInput);
//        if(!matcher.matches()) {
//            return null;
//        }
        return new BigInteger(parameterInput);
    }
}
