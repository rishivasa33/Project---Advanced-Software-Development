package dal.csci5308.project.group15.elearning.models.course;

import java.sql.SQLException;

public interface ICourse {

    int GetCourseID();
    String GetCourseName();
    String GetCourseDescription();
    boolean IsGradedCourse();



}
