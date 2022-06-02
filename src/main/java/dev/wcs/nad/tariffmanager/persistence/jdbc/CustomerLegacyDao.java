package dev.wcs.nad.tariffmanager.persistence.jdbc;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import dev.wcs.nad.tariffmanager.customer.model.VICustomer;
import org.springframework.stereotype.Component;

/*
 Import the packages with JDBC classes: java.sql.* and javax.sql.*
*/
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class CustomerLegacyDao {

    private final DataSource dataSource;

    // DataSource is configured by Spring in application.properties and injected during Context setup.
    public CustomerLegacyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Customer> getByIdJava7Syntax(long id) {
        /*
        Create the connection object
        The getConnection() method of DataSource, which is configured by Spring with the configuration of application.properties, is used to establish connection with the database.
        */

        // We use try-catch-finally introduced in Java 7
        try (Connection connection = dataSource.getConnection();
            /*
             Create the Statement object
             The preparedStatement() method of Connection interface is used to create statement. The object of statement is responsible to execute queries with the database.

             // NOTE
             // For security reasons: Always use PreparedStatements, not Statement
             */
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID=?")) {
            stmt.setLong(1, id);
            /*
             Execute the query
             The executeQuery() method of Statement interface is used to execute queries to the database. This method returns the object of ResultSet that can be used to get all the records of a table.
             */
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    /*
                     Read results from ResultSet
                     */
                    String name = resultSet.getString(1);
                    return Optional.of(new VICustomer(String.valueOf(id), name, "", LocalDate.now(), LocalDate.now()));
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Optional.empty();
    }

    /*
     * NOTE
     * This method was included for educational purposes only, please do not use this exception handling any more.
     * Use the syntax introduced in Java 7 (see above).
     */
    public Optional<Customer> getByIdBeforeJava7(long id) {
        try {
            Connection connection = dataSource.getConnection();
            try {
                CallableStatement callableStatement = connection.prepareCall("SELECT * FROM CUSTOMER WHERE ID=?");
                try {
                    ResultSet resultSet = callableStatement.executeQuery();
                    try {
                        while (resultSet.next()) {
                            String value = resultSet.getString(1);
                            System.out.println(value);
                        }
                    } finally {
                        try {
                            resultSet.close();
                        } catch (Exception ignored) {
                        }
                    }
                } finally {
                    try {
                        callableStatement.close();
                    } catch (Exception ignored) {
                    }
                }
            } finally {
                try {
                    connection.close();
                } catch (Exception ignored) {
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return Optional.empty();
    }

}
