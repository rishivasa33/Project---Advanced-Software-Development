package dal.csci5308.project.group15.elearning.utility;

import java.util.Map;

public class RedirectionsProperties extends PropertiesUtility
{
    private static PropertiesUtility uniqueInstance;

    private RedirectionsProperties()
    {
        
    }

    public static PropertiesUtility instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new PropertiesUtility();
            uniqueInstance.loadPropertiesFile("redirections.properties");
        }

        return uniqueInstance;
    }

    public Map<String, String> getPropertyMap()
    {
        return uniqueInstance.getPropertiesMap();
    }
}
