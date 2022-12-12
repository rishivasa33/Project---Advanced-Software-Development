package dal.csci5308.project.group15.elearning.deadlineNotification;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.factory.notification.CourseMaterialDeadlineNotificationFactory;
import dal.csci5308.project.group15.elearning.factory.notification.ICourseMaterialDeadlineNotificationFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CourseMaterialDeadlineNotificationHandler implements ICourseMaterialDeadlineNotificationHandler
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public CourseMaterialDeadlineNotificationHandler()
    {

    }

    public List<CourseMaterialDeadlineNotification> getCourseMaterialDeadlineNotifications()
    {
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        List<CourseMaterialDeadlineNotification> notificationList = new LinkedList<>();

        try
        {
            Map<String, List<Object>> resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_DEADLINE_NOTIFICATION"), AuthUser.getUsername());

            if(resultSet.size() == 0)
            {
                return new LinkedList<>();
            }

            for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++)
            {
                CourseMaterialDeadlineNotification notification = CourseMaterialDeadlineNotificationFactory.instance().makeCourseMaterialDeadlineNotification();

                notification.setSubjectId(String.valueOf(databaseOperations.getValueAt(resultSet, "SUBJECT_ID", row)));
                notification.setMaterialId(String.valueOf(databaseOperations.getValueAt(resultSet, "MATERIAL_ID", row)));
                notification.setMaterialName(String.valueOf(databaseOperations.getValueAt(resultSet, "DESCRIPTION_TEXT", row)));
                notification.setStartDate(String.valueOf(databaseOperations.getValueAt(resultSet, "START_DATE", row)));
                notification.setEndDate(String.valueOf(databaseOperations.getValueAt(resultSet, "END_DATE", row)));
                notification.setNumDays(String.valueOf(databaseOperations.getValueAt(resultSet, "NUM_DAYS", row)));

                notificationList.add(notification);
            }
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            logger.debug(sqlException.getStackTrace().toString());
        }

        return notificationList;
    }
}
