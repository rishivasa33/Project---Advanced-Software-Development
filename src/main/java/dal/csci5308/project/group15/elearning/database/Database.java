package dal.csci5308.project.group15.elearning.database;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Repository
public class Database
{
    private Connection conn_;
    public Connection GetConnection()
    {
        if(conn_ == null) {

            try {
                Class.forName("com.mysql.jdbc.Driver");

                conn_ = DriverManager.getConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_15_DEVINT?enabledTLSProtocols=TLSv1.2", "CSCI5308_15_DEVINT_USER", "LZQ7ss9jJS");
                conn_.setAutoCommit(false);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return conn_;
    }

    public  void CloseConnection(){
        try{
            if(conn_ != null) {
                conn_.close();
                conn_ = null;
            }
        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
    }
}