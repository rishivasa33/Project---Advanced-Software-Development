package dal.csci5308.project.group15.elearning.security;

public interface IAuthUser
{
    public String getUsername();

    public boolean isAdmin();

    public boolean isStudent();
}
