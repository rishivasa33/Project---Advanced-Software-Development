package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryInitializer;
import dal.csci5308.project.group15.elearning.models.course.BaseCourse;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.models.course.UnGradedCourse;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

import java.sql.SQLException;

public class MySqlUnGradedCoursePersistence implements UnGradedCoursePersistence {

    private MySqlCoursePersistence mySqlCoursePersistence_;
    private Database database_;

    public MySqlUnGradedCoursePersistence(MySqlCoursePersistence mySqlCoursePersistence, Database database){
        mySqlCoursePersistence_ = mySqlCoursePersistence;
        database_ = database;
    }
    public void Save(UnGradedCourse unGradedCourse) throws SQLException {
        mySqlCoursePersistence_.Save(unGradedCourse.GetCourse());
    }

    public UnGradedCourse Load(String course_id){
        BaseCourse baseCourse = mySqlCoursePersistence_.Load(course_id);
        ICourseFactory courseFactory = FactoryInitializer.instance().getCourseFactory();
        return courseFactory.CreateUngradedCourse(baseCourse.GetCourseID(), baseCourse.GetName(), baseCourse.GetDescription());
    }
}
