package dal.csci5308.project.group15.elearning.factory.notification;

import dal.csci5308.project.group15.elearning.models.deadlineNotification.CourseMaterialDeadlineNotification;
import dal.csci5308.project.group15.elearning.models.deadlineNotification.ICourseMaterialDeadlineNotificationHandler;

public interface ICourseMaterialDeadlineNotificationFactory
{
    public CourseMaterialDeadlineNotification makeCourseMaterialDeadlineNotification();
}
