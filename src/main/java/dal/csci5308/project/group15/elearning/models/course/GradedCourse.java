package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;

import java.sql.SQLException;

public class GradedCourse implements ICourse{
    Course course_;
    private int total_credits_;

    GradedCourse(String course_id, String course_name, String course_description, int total_credits){
        course_ = new Course(course_id, course_name, course_description);
        total_credits_ = total_credits;
    }

    public Course GetCourse(){
        return course_;
    }


    public void Save(GradedCoursePersistence graded_course_persistence) throws SQLException {
        graded_course_persistence.Save(this);
    }


    GradedCourse Load(GradedCoursePersistence gradedCoursePersistence, String course_id) throws SQLException {
        return gradedCoursePersistence.Load(course_id);
    }

    public int GetCredits(){
        return total_credits_;
    }

    public String GetCourseID(){
        return GetCourse().GetCourseID();
    }
    public String GetCourseName(){
        return course_.GetName();
    }
    public String GetCourseDescription(){
        return course_.GetDescription();
    }
    public boolean IsGradedCourse(){
        return  true;
    }
}
