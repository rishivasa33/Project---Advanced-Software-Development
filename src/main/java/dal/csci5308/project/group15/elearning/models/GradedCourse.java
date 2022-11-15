package dal.csci5308.project.group15.elearning.models;

public class GradedCourse extends Course{
    private int total_credits_;

    GradedCourse(int course_id, String course_name, int total_credits){
        super(course_id, course_name);
        total_credits_ = total_credits;
    }

    @Override
    public void Save(CoursePersistence course_persistence) {

    }

    @Override
    Course Load(CoursePersistence course_persistence, int course_id){
        return course_persistence.Load(course_id);
    }

    public int GetCredits(){
        return total_credits_;
    }
}
