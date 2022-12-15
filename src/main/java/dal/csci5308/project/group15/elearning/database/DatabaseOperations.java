package dal.csci5308.project.group15.elearning.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DatabaseOperations implements IDatabaseOperations {
    private static DatabaseOperations uniqueInstance;

    private DatabaseOperations() {

    }

    public static DatabaseOperations instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new DatabaseOperations();
        }

        return uniqueInstance;
    }

    @Override
    public int create(String procedureName, Object... params) throws SQLException {
        return update(procedureName, params);
    }

    @Override
    public Map<String, List<Object>> read(String procedureName, Object... params) throws SQLException {
        Map<String, List<Object>> resultSetMap = new HashMap<>();

        try (Connection connection = Database.instance().getConnection();
             CallableStatement statement = prepareCallableStatement(connection, procedureName, params);
             ResultSet resultSet = statement.executeQuery();
        ) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String columnName = resultSetMetaData.getColumnLabel(i);

                    List<Object> column;

                    if (resultSetMap.containsKey(columnName)) {
                        column = resultSetMap.get(columnName);
                    } else {
                        column = new LinkedList<>();
                    }

                    column.add(resultSet.getObject(i));
                    resultSetMap.put(columnName, column);
                }
            }
        }

        return resultSetMap;
    }

    @Override
    public int update(String procedureName, Object... params) throws SQLException {
        int result;

        try (Connection connection = Database.instance().getConnection();
             CallableStatement statement = prepareCallableStatement(connection, procedureName, params);
        ) {
            result = statement.executeUpdate();

            if (result > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
        }

        return result;
    }

    @Override
    public int delete(String procedureName, Object... params) throws SQLException {
        return update(procedureName, params);
    }

    public String prepareProceduralQuery(String procedureName, int paramsLength) {
        StringBuffer query = new StringBuffer("call ");
        query.append(procedureName);

        if (paramsLength == 0) {
            return query.append("()").toString();
        }

        query.append("(");

        int i = 0;

        while (i < paramsLength) {
            query.append("?,");
            i++;
        }

        return query.substring(0, query.length() - 1).concat(");");
    }

    private CallableStatement prepareCallableStatement(CallableStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }

        return statement;
    }

    private CallableStatement prepareCallableStatement(Connection connection, String procedureName, Object... params) throws SQLException {
        String preparedQuery = prepareProceduralQuery(procedureName, params.length);

        CallableStatement statement = connection.prepareCall(preparedQuery);
        statement = prepareCallableStatement(statement, params);

        return statement;
    }

    @Override
    public Object getValueAt(Map<String, List<Object>> map, String columnName, int row) {
        return map.get(columnName).get(row);
    }

    @Override
    public int getRowCount(Map<String, List<Object>> map) {
        if (map.isEmpty()) {
            return 0;
        }
        Iterator<String> iterator = map.keySet().iterator();
        String columnName = iterator.next();
        return map.get(columnName).size();
    }
}
