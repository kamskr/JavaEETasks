package dao.user;

import dao.GenericDAO;
import model.User;

public interface UserDAO extends GenericDAO<User, Integer> {
    User getUserByLogin(String username);
}
