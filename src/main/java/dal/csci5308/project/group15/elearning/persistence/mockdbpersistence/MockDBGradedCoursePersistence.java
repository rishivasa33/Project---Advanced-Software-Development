package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBCoursePersistence;

import java.util.ArrayList;

public class MockDBGradedCoursePersistence extends GradedCoursePersistence {

    MockDBCoursePersistence coursePersistence_;

    public MockDBGradedCoursePersistence(){
        coursePersistence_ = new MockDBCoursePersistence();
    }

    public void Save(GradedCourse course){
    }

    public GradedCourse Load(int course_id){
        Course course = coursePersistence_.Load(course_id);
        CourseFactory courseFactory = new CourseFactory();
        return courseFactory.CreateGradedCourse(course.GetCourseID(), course.GetName(), course.GetDescription(), 10);
    }

    public ArrayList<GradedCourse> GetAllGradedCourses(){
        return new ArrayList<>();
    }
}
