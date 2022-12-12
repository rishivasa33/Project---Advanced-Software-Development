package dal.csci5308.project.group15.elearning.factory.notification;

import dal.csci5308.project.group15.elearning.deadlineNotification.CourseMaterialDeadlineNotification;
import dal.csci5308.project.group15.elearning.deadlineNotification.CourseMaterialDeadlineNotificationHandler;
import dal.csci5308.project.group15.elearning.deadlineNotification.ICourseMaterialDeadlineNotificationHandler;
import dal.csci5308.project.group15.elearning.forum.IForumHandler;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;

import java.util.List;

public interface ICourseMaterialDeadlineNotificationFactory
{
    public CourseMaterialDeadlineNotification makeCourseMaterialDeadlineNotification();
    public ICourseMaterialDeadlineNotificationHandler makeCourseMaterialDeadlineNotificationHandler();
}
