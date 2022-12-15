package dal.csci5308.project.group15.elearning.factory.authUser;

import dal.csci5308.project.group15.elearning.security.authUser.IAuthUser;

public interface IAuthUserFactory
{
    public IAuthUser makeAuthUser();
    public IAuthUser makeMockAuthUser();
}
