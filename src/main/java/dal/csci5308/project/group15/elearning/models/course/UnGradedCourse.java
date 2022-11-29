package dal.csci5308.project.group15.elearning.models.course;


import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

import java.sql.SQLException;

public class UnGradedCourse implements ICourse {
    private Course course_;


    UnGradedCourse(String course_id, String course_name, String course_description){
        course_ = new Course(course_id, course_name, course_description);
    }

    public void Save(UnGradedCoursePersistence course_persistence) {
        try {
            course_persistence.Save(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Course GetCourse(){
        return course_;
    }


    UnGradedCourse Load(UnGradedCoursePersistence course_persistence, String course_id){
        return course_persistence.Load(course_id);
//    UnGradedCourse Load(UnGradedCoursePersistence course_persistence, int course_id){
//        return course_persistence.Load(course_id);
//    }



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
