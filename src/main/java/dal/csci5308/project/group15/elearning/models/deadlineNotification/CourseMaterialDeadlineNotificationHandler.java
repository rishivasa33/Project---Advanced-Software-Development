package dal.csci5308.project.group15.elearning.models.deadlineNotification;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserFactory;
import dal.csci5308.project.group15.elearning.factory.authUser.IAuthFactory;
import dal.csci5308.project.group15.elearning.factory.notification.CourseMaterialDeadlineNotificationFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.IAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CourseMaterialDeadlineNotificationHandler implements ICourseMaterialDeadlineNotificationHandler
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public CourseMaterialDeadlineNotificationHandler()
    {

    }

    public List<CourseMaterialDeadlineNotification> getCourseMaterialDeadlineNotifications(IDatabaseOperations databaseOperations, IAuthUser authUser)
    {
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        List<CourseMaterialDeadlineNotification> notificationList = new LinkedList<>();

        try
        {
            Map<String, List<Object>> resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_DEADLINE_NOTIFICATION"),
                    authUser.getUsername());

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
            logger.debug(Arrays.toString(sqlException.getStackTrace()));
        }

        return notificationList;
    }
}
