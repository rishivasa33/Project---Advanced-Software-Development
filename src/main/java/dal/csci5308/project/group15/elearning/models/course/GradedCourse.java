package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;

public class GradedCourse{
    Course course_;
    private int total_credits_;

    GradedCourse(int course_id, String course_name, String course_description, int total_credits){
        course_ = new Course(course_id, course_name, course_description);
        total_credits_ = total_credits;
    }

    public Course GetCourse(){
        return course_;
    }


    public void Save(GradedCoursePersistence graded_course_persistence) {
        graded_course_persistence.Save(this);
    }


    GradedCourse Load(GradedCoursePersistence gradedCoursePersistence, int course_id){
        return gradedCoursePersistence.Load(course_id);
    }

    public int GetCredits(){
        return total_credits_;
    }
}
