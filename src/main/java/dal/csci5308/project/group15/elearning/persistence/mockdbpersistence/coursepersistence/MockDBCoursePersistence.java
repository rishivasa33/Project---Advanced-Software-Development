package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.BaseCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CoursePersistence;

import java.util.Random;

public class MockDBCoursePersistence implements CoursePersistence {

    public void Save(BaseCourse baseCourse) {

    }

    public BaseCourse Load(String course_id) {

        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        return courseFactory.CreateBaseCourse(course_id, "test" + course_id, "test description");
    }


}
