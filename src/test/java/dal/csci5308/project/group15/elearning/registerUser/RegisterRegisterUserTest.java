package dal.csci5308.project.group15.elearning.registerUser;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.registerUser.RegisterUserFactory;
import dal.csci5308.project.group15.elearning.models.register.RegisterUser;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.registerUser.RegisterUserMockDb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class RegisterRegisterUserTest
{
    @Test
    public void createNewUserTest()
    {
        IDatabaseOperations mockDb = new RegisterUserMockDb();
        RegisterUser registerUserHandler = RegisterUserFactory.instance().makeUser();

        RegisterUser user = new RegisterUser();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test@user.com");
        user.setDefaultPassword("default");
        user.setRole("basic");
        user.setProgram("MACS");

        int result = registerUserHandler.createNewUser(mockDb, user);

        Assertions.assertEquals(1, result);
    }

    @Test
    public void getAllProgramListEmptyTest()
    {
        IDatabaseOperations mockDb = new RegisterUserMockDb();
        RegisterUser registerUserHandler = RegisterUserFactory.instance().makeUser();

        Map<String, String> programList = registerUserHandler.getAllProgramList(mockDb);

        Assertions.assertEquals(0, programList.size());
    }

    @Test
    public void getAllProgramListTest()
    {
        IDatabaseOperations mockDb = new RegisterUserMockDb();
        RegisterUser registerUserHandler = RegisterUserFactory.instance().makeUser();

        Map<String, String> programList = registerUserHandler.getAllProgramList(mockDb);

        Assertions.assertEquals(2, programList.size());
    }
}
