package service;

import dao.DAOFactory;
import dao.resource.ResourceDAO;
import model.Resource;
import model.User;

import java.util.List;

public class ResourceService {

    public List<Resource> getRelatedResources(User user) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ResourceDAO resourceDAO = factory.getResourceDAO();
        List<Resource> resources = resourceDAO.getResourceList(user);
        return resources;
    }

    public boolean createNewResourceForTheUser(User user, Resource resource) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ResourceDAO resourceDAO = factory.getResourceDAO();
        return resourceDAO.createResourceForUser(user, resource);
    }

    public Resource getResource(int id){
        DAOFactory factory = DAOFactory.getDAOFactory();
        ResourceDAO resourceDAO = factory.getResourceDAO();
        return resourceDAO.read(id);
    }
}
