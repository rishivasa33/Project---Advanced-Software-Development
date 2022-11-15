package dal.csci5308.project.group15.elearning.models;

public interface CoursePersistence {

    Course Load(int course_id);

    void Save();
}
