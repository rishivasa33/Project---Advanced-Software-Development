package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.registerUser;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.models.register.RegisterUser;

import java.sql.SQLException;
import java.util.*;

public class RegisterUserMockDatabase implements IDatabaseOperations
{
    List<RegisterUser> registeredUsers;
    Map<String, String> programMap;

    public RegisterUserMockDatabase()
    {
        registeredUsers = new LinkedList<>();
        programMap = new HashMap<>();
    }

    @Override
    public int create(String query, Object... params) throws SQLException
    {
        RegisterUser user = new RegisterUser();

        user.setFirstName(String.valueOf(params[0]));
        user.setLastName(String.valueOf(params[1]));
        user.setEmail(String.valueOf(params[2]));
        user.setDefaultPassword(String.valueOf(params[3]));
        user.setProgram(String.valueOf(params[4]));

        registeredUsers.add(user);

        return 1;
    }

    @Override
    public Map<String, List<Object>> read(String query, Object... params) throws SQLException
    {
        Map<String, List<Object>> registerUserMap = new HashMap<>();

        List<Object> programIdList = new LinkedList<>();
        List<Object> programNameList = new LinkedList<>();

        programIdList.add("Program 1");
        programNameList.add("Program Name 1");

        programIdList.add("Program 2");
        programNameList.add("Program Name 2");

        registerUserMap.put("program_id", programIdList);
        registerUserMap.put("program_name", programNameList);

        return registerUserMap;
    }

    @Override
    public int update(String query, Object... params) throws SQLException {
        return create(query, params);
    }

    @Override
    public int delete(String query, Object... params) throws SQLException {
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
