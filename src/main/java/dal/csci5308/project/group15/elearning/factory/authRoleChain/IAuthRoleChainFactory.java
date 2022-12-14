package dal.csci5308.project.group15.elearning.factory.authRoleChain;

import dal.csci5308.project.group15.elearning.security.authChain.IAuthRoleChain;

public interface IAuthRoleChainFactory
{
    public IAuthRoleChain makeAuthRoleChain();
}
