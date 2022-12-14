package dal.csci5308.project.group15.elearning.models.forum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForumComment
{
    private String comment;

    public ForumComment()
    {

    }

    public String getComment()
    {
        return this.comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ForumComment{" +
                "comment='" + comment + '\'' +
                '}';
    }
}
