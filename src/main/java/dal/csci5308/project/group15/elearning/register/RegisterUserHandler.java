package dal.csci5308.project.group15.elearning.register;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.forum.IForumHandler;
import dal.csci5308.project.group15.elearning.models.Register.User;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import dal.csci5308.project.group15.elearning.security.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class RegisterUserHandler implements IRegisterUserHandler
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public RegisterUserHandler()
    {   }

    @Override
    public int createNewUser(User user)
    {
        //  encode user password before saving to database
        Encoder encoder = new Encoder();
        String encodedPassword = encoder.encode(user.getDefaultPassword());

        try
        {
            IDatabaseOperations databaseOperations = DatabaseOperations.instance();
            return databaseOperations.create("add_new_user_student",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    encodedPassword,
                    user.getProgram());
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            return 0;
        }
    }
}
