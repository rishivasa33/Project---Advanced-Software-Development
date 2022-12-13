package dal.csci5308.project.group15.elearning.models.Register;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;

import java.util.Map;

public interface IRegisterUserHandler {
    public int createNewUser(IDatabaseOperations databaseOperations, User user);

    public Map<String, String> getAllProgramList(IDatabaseOperations databaseOperations);
}
