package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistenceSingleton;

import java.sql.SQLException;

public class Course implements ICourse{
    BaseCourse baseCourse_;
    private int total_credits_;

    GradedCoursePersistence gradedCoursePersistence_;

    Course(String course_id, String course_name, String course_description, int total_credits) {
        baseCourse_ = new BaseCourse(course_id, course_name, course_description);
        total_credits_ = total_credits;
        gradedCoursePersistence_ = GradedCoursePersistenceSingleton.GetGradedCoursePersistence();
    }

    Course(String course_id){
        baseCourse_ = new BaseCourse(course_id);
    }

    public BaseCourse GetCourse(){
        return baseCourse_;
    }


    public void Save() throws SQLException {
        gradedCoursePersistence_.Save(this);
    }


    public Course Load(String course_id) throws SQLException {
        return gradedCoursePersistence_.Load(course_id);
    }

    public int GetCredits(){
        return total_credits_;
    }

    public String GetCourseID(){
        return GetCourse().GetCourseID();
    }
    public String GetCourseName(){
        return baseCourse_.GetName();
    }
    public String GetCourseDescription(){
        return baseCourse_.GetDescription();
    }
    public boolean IsGradedCourse(){
        return  true;
    }
}
