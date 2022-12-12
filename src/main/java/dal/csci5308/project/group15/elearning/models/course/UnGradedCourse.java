package dal.csci5308.project.group15.elearning.models.course;


import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistenceSingleton;

import java.sql.SQLException;

public class UnGradedCourse implements ICourse {
    private BaseCourse baseCourse_;

    private UnGradedCoursePersistence unGradedCoursePersistence_;


    UnGradedCourse(String course_id, String course_name, String course_description){
        baseCourse_ = new BaseCourse(course_id, course_name, course_description);
        unGradedCoursePersistence_ = UnGradedCoursePersistenceSingleton.GetUnGradedCoursePersistence();
    }

    public void Save() throws SQLException {
       unGradedCoursePersistence_.Save(this);
    }


    public BaseCourse GetCourse(){
        return baseCourse_;
    }


    public UnGradedCourse Load(String course_id) throws SQLException{
       return unGradedCoursePersistence_.Load(course_id);
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
