package dao;

import dao.resource.ResourceDAO;
import dao.resource.ResourceDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;

public class MysqlDAOFactory extends DAOFactory {
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public ResourceDAO getResourceDAO() {
        return new ResourceDAOImpl();
    }
}
