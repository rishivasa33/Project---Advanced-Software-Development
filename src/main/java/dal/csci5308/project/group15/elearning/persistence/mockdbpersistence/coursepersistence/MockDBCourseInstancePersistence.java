package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseByTerm;
import dal.csci5308.project.group15.elearning.models.course.ICourseByTerm;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseInstancePersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class MockDBCourseInstancePersistence implements CourseInstancePersistence {
    @Override
    public void save(CourseByTerm courseByTerm) throws SQLException {
        courseByTerm.getCourseDetails().Save();
    }

    public CourseByTerm loadByID(String course_instance_id) throws ParseException, SQLException {
        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        Course course = courseFactory.createCourseInstanceForLoad("CSCI5100");
        course = course.Load("CSCI5100");
        return courseFactory.CreateCourseInstance("TestCInstID",course, "15/01/2023", "15/04/2022", "F22", 10, 20);
    }

    @Override
    public ArrayList<ICourseByTerm> loadByTerm(String courseTerm) throws ParseException, SQLException {
        ArrayList<ICourseByTerm> coursesByTerm = new ArrayList<>();
        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();

        Course course = courseFactory.createCourseInstanceForLoad("CSCI5100");
        course = course.Load("CSCI5100");

        ICourseByTerm courseInstance = courseFactory.CreateCourseInstance("TestCInsID1", course, "15/01/2023", "15/04/2022", "F22", 10, 20);
        coursesByTerm.add(courseInstance);

        courseInstance = courseFactory.CreateCourseInstance("TestCInsID2", course, "15/01/2023", "15/04/2022", "F22", 10, 20);
        coursesByTerm.add(courseInstance);

        courseInstance = courseFactory.CreateCourseInstance("TestCInsID3", course, "15/01/2023", "15/04/2022", "F22", 10, 20);
        coursesByTerm.add(courseInstance);

        return coursesByTerm;
    }
}
