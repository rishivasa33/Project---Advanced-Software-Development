package dal.csci5308.project.group15.elearning.models;

public class UngradedCourse extends Course{

    UngradedCourse(int course_id, String course_name){
        super(course_id, course_name);

    }

    @Override
    public void Save(CoursePersistence course_persistence) {

    }

    @Override
    Course Load(CoursePersistence course_persistence, int course_id){
        return course_persistence.Load(course_id);
    }

}
