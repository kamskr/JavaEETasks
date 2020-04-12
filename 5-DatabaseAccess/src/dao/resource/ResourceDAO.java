package dao.resource;

import dao.GenericDAO;
import model.Resource;
import model.User;

import java.util.List;

public interface ResourceDAO extends GenericDAO<Resource, Integer> {
    Resource getResourceByName(String name);
    List<Resource> getResourceList(User user);
    boolean createResourceForUser(User user, Resource resource);
}
