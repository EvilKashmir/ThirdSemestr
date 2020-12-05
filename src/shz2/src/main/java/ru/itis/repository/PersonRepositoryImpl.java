package ru.itis.repository;

import ru.itis.model.Person;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRepositoryImpl implements PersonRepository {

    //language=SQL
    private final static String SQL_FIND = "SELECT * FROM person WHERE id=?";

    //language=SQL
    private final static String SQL_SAVE = "INSERT INTO person (name, gender) VALUES (?,?)";

    //language=SQL
    private final static String SQL_DELETE = "DELETE FROM person WHERE id=?";

    //language=SQL
    private final static String SQL_UPDATE = "UPDATE person SET name=?, gender=? WHERE id=?";

    private final DataSource dataSource;

    public PersonRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Person getPersonById(long id) {
        Person result = null;
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND)) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = new Person(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("gender")
                );
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // ignore
                }
            }
        }

        return result;
    }

    @Override
    public boolean deletePersonById(long id) {
        try (Connection connection = dataSource.getConnection();

             PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean updatePersonById(long id, Person person) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {

            statement.setString(1, person.getName());
            statement.setBoolean(2, person.isGender());
            statement.setLong(3, id);


            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean savePerson(Person person) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SAVE)) {

            statement.setString(1, person.getName());
            statement.setBoolean(2, person.isGender());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
