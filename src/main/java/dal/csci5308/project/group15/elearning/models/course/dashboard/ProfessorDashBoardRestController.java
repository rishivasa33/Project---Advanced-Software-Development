package dal.csci5308.project.group15.elearning.dashboard;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.views.ViewFactoriesCollection;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentView;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentViewFactory;
import dal.csci5308.project.group15.elearning.views.course.courseContent.CreateJsonObjectFromRequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.json.JsonObject;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class ProfessorDashBoardRestController {

    @PostMapping("courseDetails/courseModuleDetails/addCourseModuleContent")
    public String AddCourseContentToModuleView(@RequestBody String requestBody)
    {
        System.out.println(requestBody);
        try {
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentView courseContentView = courseContentViewFactory.CreateJsonCourseContentView(requestBody);
            CourseContentFactory courseContentFactory = new CourseContentFactory();
            TextCourseContent textCourseContent = courseContentFactory.CreateTextCourseContent(courseContentView);
            textCourseContent.Save(courseContentView.getCourseModuleId());
            courseContentView.Update(textCourseContent);
            return courseContentView.getSerializedStringForSuccess();
        }
        catch (Exception exception){
            System.out.println("error happened in course content creation");
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentView courseContentView = courseContentViewFactory.CreateJsonCourseContentView(requestBody);
            return courseContentView.getSerializedStringForFailure();
        }
    }

    @PostMapping("courseDetails/courseModuleDetails/addCourseModuleContent/fileUpload")
    public String AddCourseContentWithFileToModuleView(@RequestParam("course_content_heading") String courseContentHeading,
                                                       @RequestParam String courseContentType,
                                                       @RequestParam String courseId,
                                                       @RequestParam String courseModuleId,
                                                       @RequestParam("contentFile") MultipartFile uploadedFile)
    {
        System.out.println(courseContentType);
        try {
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentView courseContentView = courseContentViewFactory.CreateFormDataCourseContentView(courseId,
                    courseModuleId, courseContentHeading, uploadedFile);
            return courseContentView.getSerializedStringForFailure();
        }
        catch (Exception exception){
            System.out.println("error happened in course content creation");
            CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();
            CourseContentView courseContentView = courseContentViewFactory.CreateJsonCourseContentView();
            return courseContentView.getSerializedStringForFailure();
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
            ICourse course = courseFactory.CreateGradedCourse("", "" , "", 10);
            course = course.Load(courseId);
          if(course.IsGradedCourse()){
              GradedCourse gradedCourse = (GradedCourse) course;
              ArrayList<CourseContent> courseContentsList = gradedCourse.GetCourseBase().GetAllContentsInAModule(courseModuleId);
              CourseContentView courseContentView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateJsonCourseContentView();
              return courseContentView.getSerializedStringForSuccess(courseId, courseModuleId, courseContentsList);
          }
          else{
              CourseContentView courseContentView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateJsonCourseContentView();
              return courseContentView.getSerializedStringForFailure();
          }

        }
        catch (Exception exception){
            System.out.println("error happened in course content creation");
            CourseContentView courseContentView = ViewFactoriesCollection.GetCourseContentViewFactory().CreateJsonCourseContentView();
            return courseContentView.getSerializedStringForFailure();
        }
    }

}
