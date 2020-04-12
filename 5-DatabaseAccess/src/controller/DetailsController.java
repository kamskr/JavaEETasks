package controller;

import model.Resource;
import service.ResourceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailsController")
public class DetailsController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int resourceId = Integer.parseInt(request.getParameter("id"));
        ResourceService resourceService = new ResourceService();
        Resource resource = resourceService.getResource(resourceId);
        request.setAttribute("resource", resource);
        request.getRequestDispatcher("details.jsp").forward(request, response);
    }
}
