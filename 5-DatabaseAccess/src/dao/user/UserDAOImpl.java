package dao.user;

import model.User;
import util.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static final String CREATE_USER = "INSERT INTO Users(login, firstName, lastName, password) VALUES (?,?,?,?);";
    private static final String READ_USER = "SELECT idUser, login, firstName, lastName, password FROM Users WHERE idUser = ?";
    private static final String READ_USER_BY_LOGIN = "SELECT idUser, login, firstName, lastName, password FROM Users WHERE login = ?";

    @Override
    public boolean create(User user) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)
        ){
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public User read(Integer primaryKey) {
        User resultUser = null;

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_USER)
        ){
            preparedStatement.setInt(1, primaryKey);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultUser = getUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultUser;
    }

    @Override
    public User getUserByLogin(String login) {
        User resultUser = null;

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_USER_BY_LOGIN)
        ){
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultUser = getUser(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultUser;
    }

    private User getUser(ResultSet resultSet) throws SQLException{
        User resultUser = new User();
        if (resultSet.next()) {
            resultUser.setIdUser(resultSet.getInt("idUser"));
            resultUser.setLogin(resultSet.getString("login"));
            resultUser.setFirstName(resultSet.getString("firstName"));
            resultUser.setLastName(resultSet.getString("lastName"));
            resultUser.setPassword(resultSet.getString("password"));
        }
        return resultUser;
    }
}
