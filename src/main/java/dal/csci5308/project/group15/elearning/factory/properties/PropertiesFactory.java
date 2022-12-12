package dal.csci5308.project.group15.elearning.factory.properties;

import dal.csci5308.project.group15.elearning.utility.PropertiesUtility;
import dal.csci5308.project.group15.elearning.utility.SqlProperties;

public class PropertiesFactory implements IPropertiesFactory
{
    private static PropertiesFactory uniqueInstance;

    private PropertiesFactory()
    {

    }

    public static PropertiesFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new PropertiesFactory();
        }

        return uniqueInstance;
    }

    public PropertiesUtility makeSqlProperties()
    {
        return SqlProperties.instance();
    }
}
