package dal.csci5308.project.group15.elearning.models.course;

import java.text.ParseException;

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

    public CourseInstance CreateCourseInstance(ICourse course, String start_date, String end_date) throws ParseException {
        return new CourseInstance(course, start_date, end_date);
    }

}
