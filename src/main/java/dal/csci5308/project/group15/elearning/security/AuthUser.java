package dal.csci5308.project.group15.elearning.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUser
{
    public static Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    public static String getUsername()
    {
        return auth.getName();
    }

    private static String[] getRoles()
    {
        return (String[]) auth.getAuthorities().toArray();
    }

    public static boolean isAdmin()
    {
        for(String role: getRoles())
        {
            if(role.equals("ADMIN"))
                return true;
        }

        return false;
    }

    public static boolean isUser()
    {
        for(String role: getRoles())
        {
            if(role.equals("USER"))
                return true;
        }

        return false;
    }


}