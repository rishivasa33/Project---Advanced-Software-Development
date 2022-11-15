package dal.csci5308.project.group15.elearning.models;

public class CourseFactory {
    public GradedCourse CreateGradedCourse(int course_id, String course_name, int total_credits){
        return new GradedCourse(course_id, course_name, total_credits);
    }

    public UngradedCourse CreateUngradedCourse(int course_id, String course_name){
        return new UngradedCourse(course_id, course_name);
    }

    public GradedCourse LoadGradedCourse(int course_id, CoursePersistence coursePersistence){
        return (GradedCourse) coursePersistence.Load(course_id);
    }

    public UngradedCourse LoadUnGradedCourse(int course_id, CoursePersistence coursePersistence){
        return (UngradedCourse) coursePersistence.Load(course_id);
    }
}
