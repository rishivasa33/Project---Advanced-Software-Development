package dal.csci5308.project.group15.elearning.models.course;

public class CourseFactory {
    public GradedCourse CreateGradedCourse(int course_id, String course_name, String course_description, int total_credits){
        return new GradedCourse(course_id, course_name, course_description, total_credits);
    }

    public UnGradedCourse CreateUngradedCourse(int course_id, String course_name, String course_description){
        return new UnGradedCourse(course_id, course_name, course_description);
    }

    public Course CreateCourse(int course_id, String course_name, String course_description){
        return new Course(course_id, course_name, course_description);
    }

}
