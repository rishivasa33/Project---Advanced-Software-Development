package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

public class CourseFactory {
    public GradedCourse CreateGradedCourse(int course_id, String course_name, String course_description, int total_credits){
        return new GradedCourse(course_id, course_name, course_description, total_credits);
    }

    public UngradedCourse CreateUngradedCourse(int course_id, String course_name, String course_description){
        return new UngradedCourse(course_id, course_name, course_description);
    }

    public Course CreateCourse(int course_id, String course_name, String course_description){
        return new Course(course_id, course_name, course_description);
    }

    public GradedCourse LoadGradedCourse(int course_id, GradedCoursePersistence gradedCoursePersistence){
        return gradedCoursePersistence.Load(course_id);
    }



    public UngradedCourse LoadUnGradedCourse(int course_id, UnGradedCoursePersistence unGradedCoursePersistence){
        return unGradedCoursePersistence.Load(course_id);
    }
}
