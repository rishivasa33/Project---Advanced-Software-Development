package dal.csci5308.project.group15.elearning.factory.properties;

import dal.csci5308.project.group15.elearning.properties.PropertiesUtility;

public interface IPropertiesFactory
{
    public PropertiesUtility makeSqlProperties();
    public PropertiesUtility makeRedirectionsProperties();
}
