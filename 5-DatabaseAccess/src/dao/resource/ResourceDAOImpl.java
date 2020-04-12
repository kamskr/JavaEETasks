package dao.resource;

import model.Resource;
import model.User;
import util.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAOImpl implements ResourceDAO {

    private static final String CREATE_RESOURCE = "INSERT INTO Resources(name, content) VALUES (?,?);";
    private static final String CREATE_RESOURCE_FOR_USER = "INSERT INTO Resource_User_Mapping(idResource, idUser) VALUES (?,?)";
    private static final String READ_RESOURCE = "SELECT idResource, name, content FROM Resources WHERE idResource = ?";
    private static final String READ_RESOURCE_BY_NAME = "SELECT idResource, name, content FROM Resources WHERE name = ?";
    private static final String READ_ALL_RELATED_RESOURCES= "SELECT DISTINCT idResource FROM Resource_User_Mapping WHERE idUser = ?";

    @Override
    public Resource getResourceByName(String name) {
        Resource resultResource = null;

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_RESOURCE_BY_NAME)
        ){

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultResource = getResource(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultResource;
    }

    @Override
    public boolean create(Resource resource) {
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RESOURCE)
        ){
            preparedStatement.setString(1, resource.getName());
            preparedStatement.setString(2, resource.getContent());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Resource read(Integer idResource) {
        Resource resultResource = null;

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_RESOURCE)
        ){

            preparedStatement.setInt(1, idResource);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultResource = getResource(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultResource;
    }

    public List<Resource> getResourceList(User user) {
        ArrayList<Resource> resultResources = new ArrayList<>();

        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(READ_ALL_RELATED_RESOURCES)
        ){

            preparedStatement.setInt(1, user.getIdUser());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println("adding");
                resultResources.add(read(resultSet.getInt("idResource")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultResources;
    }

    public boolean createResourceForUser(User user, Resource resource) {
        create(resource);
        resource = getResourceByName(resource.getName());
        try (Connection connection = ConnectionProvider.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_RESOURCE_FOR_USER)
        ){
            preparedStatement.setInt(1, resource.getIdResource());
            preparedStatement.setInt(2, user.getIdUser());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }
    private Resource getResource(ResultSet resultSet) throws SQLException{
        Resource resultResource = new Resource();
        if (resultSet.next()) {
            resultResource.setIdResource(resultSet.getInt("idResource"));
            resultResource.setName(resultSet.getString("name"));
            resultResource.setContent(resultSet.getString("content"));
        }
        return resultResource;
    }
}
