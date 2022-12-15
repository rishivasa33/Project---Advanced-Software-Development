package dal.csci5308.project.group15.elearning.encoder;

import dal.csci5308.project.group15.elearning.factory.encoder.EncoderFactory;
import dal.csci5308.project.group15.elearning.security.encoder.IEncoder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EncoderTest
{
    @Test
    public void encodePasswordMatchTest()
    {
        EncoderFactory encoderFactory = EncoderFactory.instance();
        IEncoder encoder = encoderFactory.makeEncoder();

        String encodedString = encoder.encode("12345");

        Assertions.assertEquals("5994471abb1112afcc18159f6cc74b4f511b9986da59b3caf5a9c173cacfc5", encodedString);
    }

    @Test
    public void encodePasswordNotMatchTest()
    {
        EncoderFactory encoderFactory = EncoderFactory.instance();
        IEncoder encoder = encoderFactory.makeEncoder();

        String encodedString = encoder.encode("12345");

        Assertions.assertNotEquals("12345", encodedString);
    }
}
