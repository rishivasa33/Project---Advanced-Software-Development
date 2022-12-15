package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.BaseCourse;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistence;

import java.sql.SQLException;
import java.util.ArrayList;

public class MockDBGradedCoursePersistence implements GradedCoursePersistence {

    MockDBCoursePersistence coursePersistence_;

    public MockDBGradedCoursePersistence() {
        coursePersistence_ = new MockDBCoursePersistence();
    }

    public void Save(Course course) throws SQLException {

    }

    public Course Load(String course_id) {
        BaseCourse baseCourse = coursePersistence_.Load(course_id);
        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        return courseFactory.CreateCourse(baseCourse.GetCourseID(), baseCourse.GetName(), baseCourse.GetDescription(), 10);
    }

    public ArrayList<Course> GetAllGradedCourses() {
        return new ArrayList<>();
    }
}
