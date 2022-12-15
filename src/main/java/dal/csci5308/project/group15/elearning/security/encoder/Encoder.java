package dal.csci5308.project.group15.elearning.security.encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.MessageDigest;

//  Code referenced from
//  https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm
public class Encoder implements IEncoder
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public Encoder()
    {

    }

    public String encode(String plainTextPassword)
    {
        String encodedString = "";

        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.toString().getBytes());

            byte[] digest = md.digest();
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < digest.length; i++)
            {
                hexString.append(Integer.toHexString(0xFF & digest[i]));
            }

            encodedString = hexString.toString();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }

        return encodedString;
    }
}