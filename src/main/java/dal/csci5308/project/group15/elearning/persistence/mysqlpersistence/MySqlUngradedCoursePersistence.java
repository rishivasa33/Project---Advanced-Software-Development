package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.UngradedCourse;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

public class MySqlUngradedCoursePersistence implements UnGradedCoursePersistence {

    private MySqlCoursePersistence mySqlCoursePersistence_;
    private Database database_;

    public MySqlUngradedCoursePersistence(MySqlCoursePersistence mySqlCoursePersistence, Database database){
        mySqlCoursePersistence_ = mySqlCoursePersistence;
        database_ = database;
    }
    public void Save(UngradedCourse ungradedCourse){
        mySqlCoursePersistence_.Save(ungradedCourse.GetCourse());
    }

    public UngradedCourse Load(int course_id){
        Course course = mySqlCoursePersistence_.Load(course_id);
        CourseFactory courseFactory = new CourseFactory();
        return courseFactory.CreateUngradedCourse(course.GetCourseID(), course.GetName(), course.GetDescription());
    }
}
