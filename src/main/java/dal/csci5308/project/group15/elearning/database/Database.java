package dal.csci5308.project.group15.elearning.database;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class Database
{
    public Connection getConnection()
    {
        Connection conn;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/asdc_group15?enabledTLSProtocols=TLSv1.2","root","password");
            conn.setAutoCommit(false);
        }

        catch (SQLException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        return conn;
    }
}