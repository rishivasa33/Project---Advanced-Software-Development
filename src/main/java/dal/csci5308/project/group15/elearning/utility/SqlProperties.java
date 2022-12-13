package dal.csci5308.project.group15.elearning.utility;

import java.util.Map;

public class SqlProperties extends PropertiesUtility
{
    private static PropertiesUtility uniqueInstance;

    private SqlProperties()
    {
        
    }

    public static PropertiesUtility instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new PropertiesUtility();
            uniqueInstance.loadPropertiesFile("sql.properties");
        }

        return uniqueInstance;
    }

    public Map<String, String> getPropertyMap()
    {
        return uniqueInstance.getPropertiesMap();
    }
}
