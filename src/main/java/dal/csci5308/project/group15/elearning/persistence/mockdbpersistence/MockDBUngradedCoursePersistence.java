package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.BaseCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.models.course.UnGradedCourse;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

import java.sql.SQLException;

public class MockDBUngradedCoursePersistence implements UnGradedCoursePersistence {

    MockDBCoursePersistence mockDBCoursePersistence;

    public MockDBUngradedCoursePersistence(){
        mockDBCoursePersistence = new MockDBCoursePersistence();
    }

    public void Save(UnGradedCourse course) throws SQLException {
        course.GetCourseBase().Save(mockDBCoursePersistence);
    }

    public UnGradedCourse Load(String course_id){
        BaseCourse baseCourse = mockDBCoursePersistence.Load(course_id);
        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        return courseFactory.CreateUngradedCourse(baseCourse.GetCourseID(), baseCourse.GetName(), baseCourse.GetDescription());
    }
}
