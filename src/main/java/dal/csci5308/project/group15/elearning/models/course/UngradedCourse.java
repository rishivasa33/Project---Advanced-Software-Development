package dal.csci5308.project.group15.elearning.models.course;


import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

public class UngradedCourse{
    private Course course_;


    UngradedCourse(int course_id, String course_name, String course_description){
        course_ = new Course(course_id, course_name, course_description);
    }

    public void Save(UnGradedCoursePersistence course_persistence) {
        course_persistence.Save(this);
    }

    public Course GetCourse(){
        return course_;
    }


    UngradedCourse Load(UnGradedCoursePersistence course_persistence, int course_id){
        return course_persistence.Load(course_id);
    }

}
