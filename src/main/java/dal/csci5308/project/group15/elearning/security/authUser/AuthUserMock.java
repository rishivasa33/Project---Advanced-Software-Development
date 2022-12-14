package dal.csci5308.project.group15.elearning.security.authUser;

public class AuthUserMock implements IAuthUser
{
    @Override
    public String getUsername()
    {
        return "Mock Auth User";
    }

    @Override
    public boolean isAdmin()
    {
        return false;
    }

    @Override
    public boolean isStudent()
    {
        return true;
    }

    @Override
    public boolean isProfessor()
    {
        return false;
    }
}
