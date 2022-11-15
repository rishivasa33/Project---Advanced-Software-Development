package dal.csci5308.project.group15.elearning.models;

public abstract class  Course {
    private int course_id_;
    private String course_name_;
    public abstract void Save(CoursePersistence course_persistence);
    abstract Course Load(CoursePersistence course_persistence, int course_id);
    public String GetName(){
        return course_name_;
    }
    Course (int course_id, String course_name){
        course_id_ = course_id;
        course_name_ = course_name;
    }

}
