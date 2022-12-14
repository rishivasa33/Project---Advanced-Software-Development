package dal.csci5308.project.group15.elearning.factory.notification;

import dal.csci5308.project.group15.elearning.models.deadlineNotification.CourseMaterialDeadlineNotification;

public class CourseMaterialDeadlineNotificationFactory implements ICourseMaterialDeadlineNotificationFactory
{
    private static CourseMaterialDeadlineNotificationFactory uniqueInstance;

    private CourseMaterialDeadlineNotificationFactory()
    {

    }

    public static CourseMaterialDeadlineNotificationFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new CourseMaterialDeadlineNotificationFactory();
        }

        return uniqueInstance;
    }

    @Override
    public CourseMaterialDeadlineNotification makeCourseMaterialDeadlineNotification() {
        return new CourseMaterialDeadlineNotification();
    }
}
