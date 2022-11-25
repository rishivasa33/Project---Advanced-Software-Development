package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.GradedCourse;

import java.util.ArrayList;

public interface GradedCoursePersistence {

    void Save(GradedCourse gradedCourse);

    GradedCourse Load(int course_id);

    ArrayList<GradedCourse> GetAllGradedCourses();
}
