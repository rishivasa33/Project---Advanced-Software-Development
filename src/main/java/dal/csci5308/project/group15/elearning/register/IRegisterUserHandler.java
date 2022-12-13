package dal.csci5308.project.group15.elearning.register;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.models.Register.User;
import java.util.Map;

public interface IRegisterUserHandler
{
    public int createNewUser(IDatabaseOperations databaseOperations, User user);

    public Map<String, String> getAllProgramList(IDatabaseOperations databaseOperations);
}
