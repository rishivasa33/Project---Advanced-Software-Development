package dal.csci5308.project.group15.elearning.database;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Database
{
    BasicDataSource dataSource;
    static Database uniqueInstance = null;

    public static Database instance()
    {
        if(null == uniqueInstance)
        {
            System.out.println("INSTANCE. Creating NEW instance of Database");

            uniqueInstance = new Database();
            return uniqueInstance;
        }

        System.out.println("INSTANCE. OLD");
        return uniqueInstance;
    }

    private Database()
    {
        System.out.println("CONSTRUCTOR Creating instance of Database");
    }

    public DataSource getDataSource()
    {
        return dataSource;
    }

    public void setDataSource(BasicDataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public Connection getConnection()
    {
        Connection connection = null;

        try
        {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        return connection;
    }
}