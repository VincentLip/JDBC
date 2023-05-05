package org.example.DAO;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Lieu;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LieuDAO extends BaseDAO<Lieu>{
    public LieuDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Lieu element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO lieu (name,adress,capacity) VALUES (?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,element.getName());
        statement.setString(2,element.getAdress());
        statement.setInt(3,element.getCapacity());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return nbRow ==1;
    }

    @Override
    public Lieu getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Lieu lieu = null;
        request = "SELECT * FROM lieu where id_lieu = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            lieu = new Lieu(resultSet.getInt("id_lieu"),
                    resultSet.getString("name"),
                    resultSet.getString("adress"),
                    resultSet.getInt("capacity")
            );
        }

        return lieu;
    }

    @Override
    public List<Lieu> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        throw new ExecutionControl.NotImplementedException("lieu DAO");
    }

    @Override
    public boolean update(Lieu element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE lieu set name = ?, adress = ?, capacity = ? where id_lieu = ?";
        _connection = new DataBaseManager().getConnection();
        statement = _connection.prepareStatement(request);
        statement.setString(1, element.getName());
        statement.setString(2, element.getAdress());
        statement.setInt(3,element.getCapacity());
        statement.setInt(4,element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }

    @Override
    public boolean delete(Lieu element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM lieu where id_lieu = ?";
        _connection = new DataBaseManager().getConnection();
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }
}
