package dal.csci5308.project.group15.elearning.security;

public class AuthUserState implements IAuthRoleState
{
    @Override
    public String getLandingPage()
    {
        return new AuthAdminState().getLandingPage();
    }
}
