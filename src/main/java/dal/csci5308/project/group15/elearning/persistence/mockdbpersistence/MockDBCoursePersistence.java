package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistence;

public class MockDBCoursePersistence implements CoursePersistence {

    public void Save(Course course){

    }

    public Course Load(int course_id){

        CourseFactory courseFactory = new CourseFactory();
        return courseFactory.CreateCourse(course_id, "test" + course_id, "test description");
    }
}
