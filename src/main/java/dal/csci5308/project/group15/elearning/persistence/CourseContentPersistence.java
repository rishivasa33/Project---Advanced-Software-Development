package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;

public interface CourseContentPersistence {
    public int Save(CourseContent courseContent);

    public CourseContent Load(int courseContentId);
}
