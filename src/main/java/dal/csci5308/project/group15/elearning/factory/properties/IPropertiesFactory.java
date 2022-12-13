package dal.csci5308.project.group15.elearning.factory.properties;

import dal.csci5308.project.group15.elearning.utility.PropertiesUtility;

public interface IPropertiesFactory
{
    public PropertiesUtility makeSqlProperties();
    public PropertiesUtility makeRedirectionsProperties();
}
