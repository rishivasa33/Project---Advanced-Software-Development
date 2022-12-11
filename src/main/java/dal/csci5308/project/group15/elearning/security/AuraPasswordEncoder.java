package dal.csci5308.project.group15.elearning.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;

public class AuraPasswordEncoder implements PasswordEncoder
{
    private static AuraPasswordEncoder uniqueInstance;

    private AuraPasswordEncoder()
    {

    }

    public static AuraPasswordEncoder instance()
    {
        if(uniqueInstance == null)
        {
            uniqueInstance = new AuraPasswordEncoder();
        }

        return uniqueInstance;
    }

    @Override
    public String encode(CharSequence plainTextPassword)
    {


        return encodedString;
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase)
    {
        return encode(plainTextPassword).equals(passwordInDatabase);
    }
}
