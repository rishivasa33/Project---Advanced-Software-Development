package dal.csci5308.project.group15.elearning.security;

public class MockAuthUser implements IAuthUser
{
    public String getUsername()
    {
        return "Mock Auth User";
    }

    public boolean isAdmin()
    {
        return false;
    }

    public boolean isStudent()
    {
        return true;
    }
}
