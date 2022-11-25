package dal.csci5308.project.group15.elearning.models.course;


import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

import java.sql.SQLException;

public class UnGradedCourse {
    private Course course_;


    UnGradedCourse(int course_id, String course_name, String course_description){
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


    UnGradedCourse Load(UnGradedCoursePersistence course_persistence, int course_id){
        return course_persistence.Load(course_id);
    }

}
