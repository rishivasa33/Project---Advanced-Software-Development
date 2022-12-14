package dal.csci5308.project.group15.elearning.security.authUser;

public interface IAuthUser
{
    public String getUsername();

    public boolean isAdmin();

    public boolean isStudent();
    public boolean isProfessor();
}
