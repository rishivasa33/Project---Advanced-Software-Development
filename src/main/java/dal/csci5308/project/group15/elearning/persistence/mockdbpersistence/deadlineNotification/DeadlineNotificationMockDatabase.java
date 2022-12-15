package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.deadlineNotification;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.models.deadlineNotification.CourseMaterialDeadlineNotification;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DeadlineNotificationMockDatabase implements IDatabaseOperations
{
    List<CourseMaterialDeadlineNotification> courseMaterialDeadlineNotificationList;

    public DeadlineNotificationMockDatabase()
    {
        courseMaterialDeadlineNotificationList = new LinkedList<>();
    }

    @Override
    public int create(String query, Object... params) throws SQLException
    {
        CourseMaterialDeadlineNotification notification = new CourseMaterialDeadlineNotification();

        notification.setEndDate("Test end date");
        notification.setMaterialId("Test material id");
        notification.setStartDate("Test start date");
        notification.setSubjectId("Test subject id");
        notification.setNumDays("10");
        notification.setMaterialName("Test material name");

        courseMaterialDeadlineNotificationList.add(notification);

        return 1;
    }

    @Override
    public Map<String, List<Object>> read(String query, Object... params) throws SQLException
    {
        Map<String, List<Object>> registerUserMap = new HashMap<>();

        List<Object> subjectId = new LinkedList<>();
        List<Object> materialId = new LinkedList<>();
        List<Object> materialName = new LinkedList<>();
        List<Object> numDays = new LinkedList<>();
        List<Object> startDate = new LinkedList<>();
        List<Object> endDate = new LinkedList<>();

        subjectId.add("Program 1");
        materialId.add("Material 1");
        materialName.add("Material Name");
        numDays.add("10");
        startDate.add("start");
        endDate.add("end");

        registerUserMap.put("SUBJECT_ID", subjectId);
        registerUserMap.put("MATERIAL_ID", materialId);
        registerUserMap.put("DESCRIPTION_TEXT", materialName);
        registerUserMap.put("START_DATE", startDate);
        registerUserMap.put("END_DATE", endDate);
        registerUserMap.put("NUM_DAYS", numDays);

        return registerUserMap;
    }

    @Override
    public int update(String query, Object... params) throws SQLException
    {
        return create(query, params);
    }

    @Override
    public int delete(String query, Object... params) throws SQLException
    {
        return create(query, params);
    }

    @Override
    public Object getValueAt(Map<String, List<Object>> map, String columnName, int row)
    {
        return map.get(columnName).get(row);
    }

    @Override
    public int getRowCount(Map<String, List<Object>> map)
    {
        Iterator<String> iterator = map.keySet().iterator();
        String columnName = iterator.next();
        return  map.get(columnName).size();
    }
}
