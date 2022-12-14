package dal.csci5308.project.group15.elearning.security.authChain;

public class AuthRoleChain implements IAuthRoleChain
{
    @Override
    public String getLandingPage()
    {
        return new AuthAdminChain().getLandingPage();
    }
}
