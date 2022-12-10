package dal.csci5308.project.group15.elearning.database;

import java.util.List;

public interface IDatabase
{
    public int create(String query, Object... params);
    public List<List<String>> read(String query, Object... params);
    public int update(String query, Object... params);
    public int delete(String query, Object... params);
}
