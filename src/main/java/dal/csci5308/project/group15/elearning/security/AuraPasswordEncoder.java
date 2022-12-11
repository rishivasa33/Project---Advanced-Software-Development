package dal.csci5308.project.group15.elearning.security;

import dal.csci5308.project.group15.elearning.factory.encoder.EncoderFactory;
import dal.csci5308.project.group15.elearning.factory.encoder.IEncoder;
import dal.csci5308.project.group15.elearning.factory.encoder.IEncoderFactory;
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
        IEncoder encoder = EncoderFactory.instance().makeEncoder();
        return encoder.encode(plainTextPassword.toString());
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase)
    {
        return encode(plainTextPassword).equals(passwordInDatabase);
    }
}
