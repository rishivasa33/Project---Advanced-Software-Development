package dal.csci5308.project.group15.elearning.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

//  Referenced code from
//  https://mkyong.com/java/java-properties-file-examples/

public class PropertiesUtility implements IPropertiesUtility
{
    private Map<String, String> propertiesMap;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PropertiesUtility()
    {

    }

    public void loadPropertiesFile(String filename)
    {
        logger.debug("Loading " + filename + " properties file.");
        propertiesMap = new HashMap<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename))
        {
            Properties properties = new Properties();

            if (inputStream == null)
            {
                logger.error("Unable to find " + filename);
                return;
            }

            properties.load(inputStream);
            Set<Object> objects = properties.keySet();

            Enumeration e = properties.propertyNames();

            while (e.hasMoreElements())
            {
                String key = (String) e.nextElement();
                String value = properties.getProperty(key);
                logger.trace("Key : " + key + ", Value : " + value);
                propertiesMap.put(key, value);
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public Map<String, String> getPropertiesMap()
    {
        return propertiesMap;
    }
}
