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
        String encodedString = "";

        try
        {
            //Creating the MessageDigest object
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            //Passing data to the created MessageDigest Object
            md.update(plainTextPassword.toString().getBytes());

            //Compute the message digest
            byte[] digest = md.digest();
            System.out.println(digest);

            //Converting the byte array in to HexString format
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < digest.length; i++)
            {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }

            encodedString = hexString.toString();

//            System.out.println("Hex format here: " + encodedString);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return encodedString;
    }

    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase)
    {
        return encode(plainTextPassword).equals(passwordInDatabase);
    }
}
