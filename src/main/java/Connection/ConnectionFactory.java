package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Aceasta clasa efectueaza conexiunea cu baza de date.
 * @author tania
 */

public class ConnectionFactory {

        private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
        private static final String DRIVER = "com.mysql.jdbc.Driver";
        private static final String DBURL = "jdbc:mysql://localhost:3306/tema3tp";
        private static final String USER = "TaniaSouca";
        private static final String PASS = "T.2486";

        private static ConnectionFactory singleInstance = new ConnectionFactory();

        private ConnectionFactory() {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        private Connection createConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DBURL, USER, PASS);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
                e.printStackTrace();
            }
            return connection;
        }

        public static void close(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
                }
            }
        }

        public static void close(Statement statement) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
                }
            }
        }

        public static void close(ResultSet resultSet) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
                }
            }
        }
        public static Connection getConnection() {
        return singleInstance.createConnection();
    }
}

