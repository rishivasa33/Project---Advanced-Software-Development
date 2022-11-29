package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.CourseInstance;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.persistence.CourseInstancePersistence;

import java.text.ParseException;

public class MockDBCourseInstancePersistence implements CourseInstancePersistence {

    public void Save(CourseInstance courseInstance){

        //courseInstance.GetCourse().

    }

    public CourseInstance Load(int course_instance_id) throws ParseException {
        CourseFactory courseFactory = new CourseFactory();
        GradedCourse gradedCourse = courseFactory.CreateGradedCourse(course_instance_id, "test", "testd",
                10);
        return courseFactory.CreateCourseInstance(gradedCourse, "15/01/2023", "15/04/2022");
    }
}
