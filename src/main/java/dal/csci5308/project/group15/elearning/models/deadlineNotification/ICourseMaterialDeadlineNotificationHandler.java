package dal.csci5308.project.group15.elearning.models.deadlineNotification;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.security.IAuthUser;

import java.util.List;

public interface ICourseMaterialDeadlineNotificationHandler
{
    public List<CourseMaterialDeadlineNotification> getCourseMaterialDeadlineNotifications(IDatabaseOperations databaseOperations, IAuthUser authUser);
}
