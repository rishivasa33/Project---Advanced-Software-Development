package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.FileCourseContentPersistence;

import java.sql.SQLException;

public class MockDBFileCourseContentPersistence implements FileCourseContentPersistence {
    @Override
    public int Save(FileCourseContent pdfFileCourseContent, int courseModuleId) throws SQLException {
        return 1;
    }

    @Override
    public FileCourseContent Load(int fileCourseContentId) throws SQLException {
      FileCourseContent fileCourseContent =   FactoryFacade.instance().getCourseContentFactory().CreateFileCourseContent(1,
              "content", "filepath");
      return fileCourseContent;
    }
}
