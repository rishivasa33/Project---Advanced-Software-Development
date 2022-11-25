package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.UngradedCourse;

public interface UnGradedCoursePersistence {

    UngradedCourse Load(int course_id);

    void Save(UngradedCourse course);
}
