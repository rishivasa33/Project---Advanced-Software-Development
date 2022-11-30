package dal.csci5308.project.group15.elearning.models.course;

import java.sql.SQLException;

public interface ICourse {

    String GetCourseID();
    String GetCourseName();
    String GetCourseDescription();
    boolean IsGradedCourse();

    void Save() throws SQLException;

    ICourse Load(String course_id) throws SQLException;



}
