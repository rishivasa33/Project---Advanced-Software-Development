package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistence;

import java.util.Random;

public class MockDBCoursePersistence implements CoursePersistence {

    public void Save(Course course){

    }

    public Course Load(int course_id){

        CourseFactory courseFactory = new CourseFactory();
        return courseFactory.CreateCourse(course_id, "test" + course_id, "test description");
    }

    public int GenerateUniqueCourseID(){
        int minimum = 0;
        int maximum = Integer.MAX_VALUE - 10;
        Random rand = new Random();
        int n = maximum - minimum + 1;
        int i = rand.nextInt() % n;
        int randomNum =  minimum + i;
        return randomNum;
    }
}
