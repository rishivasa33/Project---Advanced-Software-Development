package dal.csci5308.project.group15.elearning.properties;

import java.util.Map;

public interface IPropertiesUtility
{
    public void loadPropertiesFile(String filename);
    public Map<String, String> getPropertiesMap();
}
