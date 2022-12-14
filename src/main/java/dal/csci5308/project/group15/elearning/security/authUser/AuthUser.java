package dal.csci5308.project.group15.elearning.security.authUser;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUser implements IAuthUser
{
    private Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public AuthUser()
    {

    }

    public Authentication getInstance()
    {
        return auth;
    }

    public String getUsername()
    {
        return auth.getName();
    }

    public boolean isAdmin()
    {
        for(GrantedAuthority role: auth.getAuthorities())
        {
            if(role.getAuthority().contains("admin"))
                return true;
        }

        return false;
    }

    public boolean isStudent()
    {
        for(GrantedAuthority role: auth.getAuthorities())
        {
            if(role.getAuthority().equals("student"))
                return true;
        }

        return false;
    }

    public boolean isProfessor()
    {
        for(GrantedAuthority role: auth.getAuthorities())
        {
            if(role.getAuthority().contains("professor"))
                return true;
        }

        return false;
    }
}