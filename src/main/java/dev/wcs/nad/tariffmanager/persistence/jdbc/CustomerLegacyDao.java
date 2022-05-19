package dev.wcs.nad.tariffmanager.persistence.jdbc;

import dev.wcs.nad.tariffmanager.customer.model.Customer;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Component
public class CustomerLegacyDao {

    private final DataSource dataSource;

    // DataSource is configured by Spring in application.properties and injected during Context setup.
    public CustomerLegacyDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Customer> getByIdJava7Syntax(int id) {
        // We use try-catch-finally introduced in Java 7
        try (Connection connection = dataSource.getConnection();
             // NOTE
             // For security reasons: Always use PreparedStatements, not Statement
             PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CUSTOMER WHERE ID=?")) {
            stmt.setLong(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    String value = resultSet.getString(1);
                    System.out.println(value);
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
