package dal.csci5308.project.group15.elearning.factory.encoder;

import dal.csci5308.project.group15.elearning.security.IEncoder;

public interface IEncoderFactory
{
    public IEncoder makeEncoder();
}
