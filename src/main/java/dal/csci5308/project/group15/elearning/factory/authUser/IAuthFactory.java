package dal.csci5308.project.group15.elearning.factory.authUser;

import dal.csci5308.project.group15.elearning.security.IAuthUser;

public interface IAuthFactory
{
    public IAuthUser makeAuthUser();
    public IAuthUser makeMockAuthUser();
}
