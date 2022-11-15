package dal.csci5308.project.group15.elearning.models;

public class MockDBCoursePersistence implements CoursePersistence {

    public void Save(){

    }

    public Course Load(int course_id){

        if(course_id %2 == 0)
            return new UngradedCourse(course_id, "test" + course_id);
        else {
            return new GradedCourse(course_id, "test" + course_id, 20);
        }
    }
}
