package org.example.DAO;

import jdk.jshell.spi.ExecutionControl;
import org.example.model.Evenement;
import org.example.model.Lieu;
import org.example.util.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EvenementDAO extends BaseDAO<Evenement> {
    public EvenementDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean save(Evenement element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "INSERT INTO evenement (id_lieu,name,date,hour,price,nb_ticket) VALUES (?,?,?,?,?,?)";
        statement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1,element.getLieu().getId());
        statement.setString(2,element.getName());
        statement.setString(3,element.getDate());
        statement.setString(4,element.getHour());
        statement.setFloat(5,element.getPrice());
        statement.setInt(6,element.getNbTicketSold());
        int nbRow = statement.executeUpdate();
        resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            element.setId(resultSet.getInt(1));
        }
        return nbRow ==1;
    }


    @Override
    public Evenement getById(int id) throws ExecutionControl.NotImplementedException, SQLException {
        Evenement evenement = null;
        request = "SELECT * FROM evenement where id_evenement = ?";
        statement = _connection.prepareStatement(request);
        statement.setInt(1,id);
        resultSet = statement.executeQuery();
        if(resultSet.next()) {
            evenement = new Evenement(resultSet.getInt("id_evenement"),
                    resultSet.getString("name"),
                    resultSet.getString("hour"),
                    resultSet.getString("date"),
                    (Lieu) resultSet.getObject("id_lieu"),
                    resultSet.getFloat("price"),
                    resultSet.getInt("nb_ticket")
            );
        }

        return evenement;
    }

    @Override
    public List<Evenement> getAll() throws ExecutionControl.NotImplementedException, SQLException {
        return null;
    }

    @Override
    public boolean update(Evenement element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "UPDATE evenement set id_lieu = ?,name = ?, date = ?, hour = ? ,price=?,nb_ticket=? where id_lieu = ?";
        _connection = new DataBaseManager().getConnection();
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getLieu().getId());
        statement.setString(2, element.getName());
        statement.setString(3,element.getDate());
        statement.setString(5,element.getHour());
        statement.setFloat(5,element.getPrice());
        statement.setInt(5,element.getNbTicketSold());
        statement.setInt(5,element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }

    @Override
    public boolean delete(Evenement element) throws ExecutionControl.NotImplementedException, SQLException {
        request = "DELETE FROM evenement where id_evenement = ?";
        _connection = new DataBaseManager().getConnection();
        statement = _connection.prepareStatement(request);
        statement.setInt(1, element.getId());
        int nbRow = statement.executeUpdate();
        return nbRow == 1;
    }
}
