package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.persistence.CoursePersistence;

import java.sql.SQLException;

public class  Course {
    private String course_id_;
    private String course_name_;

    private String course_description_;

    public void Save(CoursePersistence course_persistence) throws SQLException {
        course_persistence.Save(this);
    }
    Course Load(CoursePersistence course_persistence, String course_id){
        return course_persistence.Load(course_id);
    }
    public String GetName(){
        return course_name_;
    }


    public String GetDescription(){
        return course_description_;
    }
    Course (String course_id, String course_name, String course_description){
        course_id_ = course_id;
        course_name_ = course_name;
        course_description_ = course_description;
    }

    public String GetCourseID(){
        return  course_id_;
    }

}