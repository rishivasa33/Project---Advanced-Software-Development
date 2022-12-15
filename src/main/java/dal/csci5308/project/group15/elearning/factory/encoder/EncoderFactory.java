package dal.csci5308.project.group15.elearning.factory.encoder;

import dal.csci5308.project.group15.elearning.security.encoder.Encoder;
import dal.csci5308.project.group15.elearning.security.encoder.IEncoder;

public class EncoderFactory implements IEncoderFactory
{
    private static EncoderFactory uniqueInstance;

    private EncoderFactory()
    {

    }

    public static EncoderFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new EncoderFactory();
        }

        return uniqueInstance;
    }


    @Override
    public IEncoder makeEncoder() {
        return new Encoder();
    }
}
