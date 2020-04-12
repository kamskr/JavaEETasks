package controller;

import model.Resource;
import model.User;
import service.ResourceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ResourcesController")
public class ResourcesController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        String name = request.getParameter("inputName");
        String content = request.getParameter("inputContent");
        Resource resource = new Resource();
        resource.setName(name);
        resource.setContent(content);
        ResourceService resourceService = new ResourceService();
        if(resourceService.createNewResourceForTheUser((User)session.getAttribute("user"), resource)){
            request.getRequestDispatcher("resources.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResourceService resourceService = new ResourceService();
        HttpSession session = request.getSession();
        List<Resource> resources = resourceService.getRelatedResources((User)session.getAttribute("user"));
        session.setAttribute("listOfResources", resources);
        request.getRequestDispatcher("resources.jsp").forward(request,response);

    }
}
