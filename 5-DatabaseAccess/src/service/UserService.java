package service;

import dao.DAOFactory;
import dao.resource.ResourceDAO;
import dao.user.UserDAO;
import model.User;

public class UserService {

    public void addUser(String login, String firstName, String lastName,String password){
        User user = new User(login, firstName, lastName, password);
        System.out.println(user);
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        userDAO.create(user);
    }

    public User getUserById(int userId){
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.read(userId);
        return user;
    }

    public User getByLogin(String login){
        DAOFactory factory = DAOFactory.getDAOFactory();
        UserDAO userDAO = factory.getUserDAO();
        User user = userDAO.getUserByLogin(login);
        return user;
    }

    public boolean authenticateUser(String login, String password) {
        User user = getByLogin(login);
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
