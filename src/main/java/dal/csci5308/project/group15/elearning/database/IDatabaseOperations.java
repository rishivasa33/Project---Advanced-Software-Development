package dal.csci5308.project.group15.elearning.database;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDatabaseOperations
{
    public int create(String query, Object... params) throws SQLException;
    public Map<String, List<Object>> read(String query, Object... params) throws SQLException;
    public int update(String query, Object... params) throws SQLException;
    public int delete(String query, Object... params) throws SQLException;
    public Object getValueAt(Map<String, List<Object>> map, String columnName, int row);
    public int getRowCount(Map<String, List<Object>> map);
}
