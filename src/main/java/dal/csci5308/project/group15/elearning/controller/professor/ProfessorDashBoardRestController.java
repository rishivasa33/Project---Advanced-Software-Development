package dal.csci5308.project.group15.elearning.controller.professor;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.ICourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.views.ViewFactoriesCollection;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentRequestView;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentResponseView;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentViewFactory;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CreateJsonObjectFromRequestBody;
import dal.csci5308.project.group15.elearning.views.course.courseContent.FetchFileContentResponseView;
import dal.csci5308.project.group15.elearning.views.course.courseContent.FetchModuleContentFileRequestView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.json.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("professor/")
public class ProfessorDashBoardRestController {

    @PostMapping("courseDetails/courseModuleDetails/addCourseModuleContent")
    public String AddCourseContentToModuleView(@RequestBody String requestBody)
    {
        try {
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentRequestView courseContentView = courseContentViewFactory.CreateJsonCourseContentView(requestBody);
            ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();
            TextCourseContent textCourseContent = courseContentFactory.CreateTextCourseContent(courseContentView);
            textCourseContent.Save(courseContentView.getCourseModuleId());
            CourseContentResponseView courseContentResponseView = courseContentViewFactory.CreateTextCourseContentResponseView(courseContentView.getCourseId(), courseContentView.getCourseModuleId(), textCourseContent);
            return courseContentResponseView.getSerializedStringForSuccess();
        }
        catch (Exception exception){
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentRequestView courseContentView = courseContentViewFactory.CreateJsonCourseContentView(requestBody);
            return CourseContentResponseView.getSerializedStringForFailure();
        }
    }

    @PostMapping("courseDetails/courseModuleDetails/addCourseModuleContent/fileUpload")
    public String AddCourseContentWithFileToModuleView(@RequestParam("course_content_heading") String courseContentHeading,
                                                       @RequestParam String courseContentType,
                                                       @RequestParam String courseId,
                                                       @RequestParam String courseModuleId,
                                                       @RequestParam("contentFile") MultipartFile uploadedFile)
    {
        try {
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentRequestView courseContentView = courseContentViewFactory.CreateFormDataCourseContentView(courseId,
                    courseModuleId, courseContentHeading, uploadedFile);
            ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();
            FileCourseContent fileCourseContent =  courseContentFactory.CreateFileCourseContent(courseContentView);
            if(!fileCourseContent.GetState().IsFileValid()){
                return CourseContentResponseView.getSerializedStringForFailure();
            }
            else {
                fileCourseContent.Save(courseContentView.getCourseModuleId());
                CourseContentResponseView courseContentResponseView = courseContentViewFactory.CreateFileCourseContentResponseView(courseContentView.getCourseId(), courseContentView.getCourseModuleId(), fileCourseContent);
                return courseContentResponseView.getSerializedStringForSuccess();
            }
        }
        catch (Exception exception){
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentRequestView courseContentView = courseContentViewFactory.CreateJsonCourseContentView();
            return CourseContentResponseView.getSerializedStringForFailure();
        }
    }

    @PostMapping("courseDetails/courseModuleDetails/getAllContents")
    public String CourseModuleContentView(@RequestBody String requestBody)
    {
        try {
           JsonObject jsonObject =  CreateJsonObjectFromRequestBody.GetJsonObjectFromRequestBody(requestBody);
           String courseId = jsonObject.getString("courseId");
           int courseModuleId = Integer.parseInt(jsonObject.getString("courseModuleId"));
            CourseFactory courseFactory = new CourseFactory();
            ICourse course = courseFactory.CreateCourse("", "" , "", 10);
            course = course.Load(courseId);
          if(course.IsGradedCourse()){
              Course gradedCourse = (Course) course;
              ArrayList<CourseContent> courseContentsList = gradedCourse.GetCourseBase().GetAllContentsInAModule(courseModuleId);
             CourseContentResponseView courseContentResponseView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateTextCourseContentResponseView(courseId, courseModuleId);
              return courseContentResponseView.getSerializedStringForSuccess(courseId, courseModuleId, courseContentsList);
          }
          else{
               return CourseContentResponseView.getSerializedStringForFailure();
          }

        }
        catch (Exception exception){
            return CourseContentResponseView.getSerializedStringForFailure();
        }
    }

    @RequestMapping(value="courseDetails/fetchModuleContentFile", method=RequestMethod.POST)
    public ResponseEntity<byte[]> getFile(@RequestBody String json) {
        try {
            JsonObject jsonObject = CreateJsonObjectFromRequestBody.GetJsonObjectFromRequestBody(json);
            FetchModuleContentFileRequestView fetchModuleContentFileRequestView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateFetchModuleContentFileRequestView(
                    jsonObject
            );
           Course course =  FactoryFacade.instance().getCourseFactory().LoadCourseFromPersistence(fetchModuleContentFileRequestView.getCourseId());
           course = course.Load(fetchModuleContentFileRequestView.getCourseId());
           FileCourseContent fileCourseContent =  course.GetCourseBase().GetContentFilePath(fetchModuleContentFileRequestView.getModuleId(), fetchModuleContentFileRequestView.getModuleContentId());
           FetchFileContentResponseView fetchFileContentResponseView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateFetchFileContentResponseView(fileCourseContent);
           return fetchFileContentResponseView.GetResponseForSuccess();

        }
        catch (SQLException exception ){
            return FetchFileContentResponseView.GetResponseForFailure();
        }
    }

    @RequestMapping(value="courseDetails/fetchModuleContentFile/{courseId}/{courseModuleId}/{courseModuleContentId}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> getFile(@PathVariable String courseId, @PathVariable String courseModuleId,
                                             @PathVariable String courseModuleContentId) {
        try {

            FetchModuleContentFileRequestView fetchModuleContentFileRequestView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateFetchModuleContentFileRequestView(
                    courseId, courseModuleId, courseModuleContentId);
            Course course =  FactoryFacade.instance().getCourseFactory().LoadCourseFromPersistence(fetchModuleContentFileRequestView.getCourseId());
            course = course.Load(fetchModuleContentFileRequestView.getCourseId());
            FileCourseContent fileCourseContent =  course.GetCourseBase().GetContentFilePath(fetchModuleContentFileRequestView.getModuleId(), fetchModuleContentFileRequestView.getModuleContentId());
            FetchFileContentResponseView fetchFileContentResponseView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateFetchFileContentResponseView(fileCourseContent);
            return fetchFileContentResponseView.GetResponseForSuccess();

        }
        catch (SQLException exception ){
            return FetchFileContentResponseView.GetResponseForFailure();
        }
    }

}
