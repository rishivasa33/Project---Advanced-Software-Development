package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.UnGradedCourse;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

import java.sql.SQLException;

public class MockDBUngradedCoursePersistence implements UnGradedCoursePersistence {

    MockDBCoursePersistence mockDBCoursePersistence;

    public MockDBUngradedCoursePersistence(){
        mockDBCoursePersistence = new MockDBCoursePersistence();
    }

    public void Save(UnGradedCourse course) throws SQLException {
        course.GetCourse().Save(mockDBCoursePersistence);
    }

    public UnGradedCourse Load(String course_id){
        Course course = mockDBCoursePersistence.Load(course_id);
        CourseFactory courseFactory = new CourseFactory();
        return courseFactory.CreateUngradedCourse(course.GetCourseID(), course.GetName(), course.GetDescription());
    }
}
