package dal.csci5308.project.group15.elearning.models.course.coursecontent;


import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.ICourseContentFactory;
import dal.csci5308.project.group15.elearning.views.ViewFactoriesCollection;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentRequestView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestFileCourseContent {

    @Test
    void TestFileCourseContentCreation(){

        try {
            ICourseContentFactory courseContentFactoryFactory = FactoryFacade.instance().getCourseContentFactory();
            FileCourseContent fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(1, "Content1", "path");
            Assertions.assertTrue(true);
        }
        catch (Exception exception){
            Assertions.fail();
        }

        try {
            ICourseContentFactory courseContentFactoryFactory = FactoryFacade.instance().getCourseContentFactory();
            FileCourseContent fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent("Content1", "path");
            Assertions.assertTrue(true);
        }
        catch (Exception exception){
            Assertions.fail();
        }

        try {
            ICourseContentFactory courseContentFactoryFactory = FactoryFacade.instance().getCourseContentFactory();
            CourseContentRequestView courseContentRequestView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateFormDataCourseContentView(
                    "1","1", "heading", new MockMultipartFile("file", new byte[5])
            );
            FileCourseContent fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(courseContentRequestView);
            Assertions.assertTrue(true);
        }
        catch (Exception exception){
            Assertions.fail();
        }


    }

    @Test
    void TestFileCourseContentAttributes(){

        try {
            ICourseContentFactory courseContentFactoryFactory = FactoryFacade.instance().getCourseContentFactory();
            FileCourseContent fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(1, "Content1", "filepath");
            Assertions.assertEquals(fileCourseContent.GetFilePath(), "filepath");
            Assertions.assertEquals(fileCourseContent.GetContentId(), 1);
            Assertions.assertEquals(fileCourseContent.IsTextContent(), false);
            Assertions.assertEquals(fileCourseContent.GetContentHeading(), "Content1");
            Assertions.assertTrue(true);
        }
        catch (Exception exception){
            Assertions.fail();
        }
    }

    @Test
    void TestFileCourseSave(){

        try {
            ICourseContentFactory courseContentFactoryFactory = FactoryFacade.instance().getCourseContentFactory();
            FileCourseContent fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(1, "Content1", "filepath");
            fileCourseContent.Save(2);
            Assertions.assertTrue(true);
        }
        catch (Exception exception){
            Assertions.fail();
        }
    }

    @Test
    void TestFileCourseLoad(){

        try {
            ICourseContentFactory courseContentFactoryFactory = FactoryFacade.instance().getCourseContentFactory();
            FileCourseContent fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(1, "Content1", "filepath");
            fileCourseContent.Save(2);
            Assertions.assertTrue(true);
        }
        catch (Exception exception){
            Assertions.fail();
        }
    }


    @Test
    void TestFileCourseContentFileName(){

        try {
            ICourseContentFactory courseContentFactoryFactory = FactoryFacade.instance().getCourseContentFactory();
            FileCourseContent fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(1, "Content1", "filepath.txt");
            Assertions.assertEquals(fileCourseContent.GetFileName(), "filepath.txt");
            fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(1, "Content1", "files/filepath.txt");
            Assertions.assertEquals(fileCourseContent.GetFileName(), "filepath.txt");

            fileCourseContent = courseContentFactoryFactory.CreateFileCourseContent(1, "Content1", "files/filepath");
            Assertions.assertEquals(fileCourseContent.GetFileName(), "filepath");
        }
        catch (Exception exception){
            Assertions.fail();
        }
    }
}
