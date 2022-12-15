package dal.csci5308.project.group15.elearning.properties;

import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PropertiesFileTest
{
    @Test
    public void checkSqlPropertiesFileExist()
    {
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        Map<String, String> map = propertiesFactory.makeSqlProperties().getPropertiesMap();

        Assertions.assertNotNull(map);
        Assertions.assertNotEquals(0, map.size());
    }

    @Test
    public void checkRedirectionsPropertiesFileExist()
    {
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        Map<String, String> map = propertiesFactory.makeRedirectionsProperties().getPropertiesMap();

        Assertions.assertNotNull(map);
        Assertions.assertNotEquals(0, map.size());
    }
}
