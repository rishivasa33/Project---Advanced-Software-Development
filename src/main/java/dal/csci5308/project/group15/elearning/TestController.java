package dal.csci5308.project.group15.elearning;

import dal.csci5308.project.group15.elearning.database.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/")
public class TestController 
{
	@GetMapping("/health")
	public String healthCheck()
	{
		Connection connection = Database.instance().getConnection();

		PreparedStatement statement = null;
		try
		{
			statement = connection.prepareStatement("select * from user where username = ?");
			statement.setString(1, "anujdawar");

			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next())
			{
				String userId = resultSet.getString("ID");
				String firstName = resultSet.getString("FIRST_NAME");
				String lastName = resultSet.getString("LAST_NAME");

				System.out.println("ID: " + userId);
				System.out.println("FIRST NAME: " + firstName);
				System.out.println("LAST NAME: " + lastName);
			}
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}

		return "OK!";
	}
}
