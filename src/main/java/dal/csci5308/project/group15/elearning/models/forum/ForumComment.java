package dal.csci5308.project.group15.elearning.models.forum;

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
