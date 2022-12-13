package dal.csci5308.project.group15.elearning.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUser
{
    public static Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public static String getUsername()
    {
        return auth.getName();
    }

    public static boolean isAdmin()
    {
        for(GrantedAuthority role: auth.getAuthorities())
        {
            if(role.getAuthority().contains("admin"))
                return true;
        }

        return false;
    }

    public static boolean isStudent()
    {
        for(GrantedAuthority role: auth.getAuthorities())
        {
            if(role.getAuthority().equals("student"))
                return true;
        }

        return false;
    }


    public static boolean isProfessor()
    {
        for(GrantedAuthority role: auth.getAuthorities())
        {
            if(role.getAuthority().equals("professor"))
                return true;
        }

        return false;
    }



}