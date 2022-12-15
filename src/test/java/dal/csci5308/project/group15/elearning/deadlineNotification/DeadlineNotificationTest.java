package dal.csci5308.project.group15.elearning.deadlineNotification;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.authUser.IAuthUserFactory;
import dal.csci5308.project.group15.elearning.factory.notification.CourseMaterialDeadlineNotificationFactory;
import dal.csci5308.project.group15.elearning.models.deadlineNotification.CourseMaterialDeadlineNotification;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.deadlineNotification.DeadlineNotificationMockDatabase;
import dal.csci5308.project.group15.elearning.security.authUser.IAuthUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeadlineNotificationTest
{
    @Test
    public void getCourseMaterialDeadlineNotificationTest()
    {
        IDatabaseOperations mockDb = new DeadlineNotificationMockDatabase();
        IAuthUserFactory authFactory = AuthUserUserFactory.instance();
        IAuthUser mockAuthUser = authFactory.makeMockAuthUser();

        CourseMaterialDeadlineNotification registerUserHandler = CourseMaterialDeadlineNotificationFactory.instance().makeCourseMaterialDeadlineNotification();
        List<CourseMaterialDeadlineNotification> courseMaterialDeadlineNotificationList = registerUserHandler.getCourseMaterialDeadlineNotifications(mockDb, mockAuthUser);
        Assertions.assertEquals(1, courseMaterialDeadlineNotificationList.size());
    }
}
