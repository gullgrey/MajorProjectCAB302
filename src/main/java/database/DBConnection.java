package main.java.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * TODO
 */
public class DBConnection {

    private static Connection connection = null;

    /**
     * Constructor initializes the connection to a database.
     *
     * @param propsFile A props file that contains a database: url, username, password and schema.
     * @throws SQLException when the supplied props file contains props that are not able to access the database.
     * @throws IOException when the propsFile string is not a path to a propsFile.
     */
    public DBConnection(String propsFile) throws SQLException, IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream(propsFile);
        props.load(in);
        in.close();

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");
        String schema = props.getProperty("jdbc.schema");

        connection = DriverManager.getConnection(url + "/" + schema, username,
                password);

    }

    public static Connection getInstance(String propsFile) throws SQLException, IOException{
        if (connection == null) {
            new DBConnection(propsFile);
        }
        return connection;
    }

//    /**
//     * Retrieves the connection that is initialised in the constructor.
//     *
//     * @return a connection instance of the database.
//     */
//    public Connection getConnection() {
//        return connection;
//    }

}
