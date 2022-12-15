package dal.csci5308.project.group15.elearning.factory.properties;

import dal.csci5308.project.group15.elearning.properties.PropertiesUtility;
import dal.csci5308.project.group15.elearning.properties.RedirectionsProperties;
import dal.csci5308.project.group15.elearning.properties.SqlProperties;

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

    @Override
    public PropertiesUtility makeRedirectionsProperties() {
        return RedirectionsProperties.instance();
    }
}
