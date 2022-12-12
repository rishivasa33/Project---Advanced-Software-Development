package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import org.springframework.web.multipart.MultipartFile;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

public class FormDataCourseContentView implements CourseContentView{

    private final String courseId;
    private final int courseModuleId;

    private final Integer courseModuleContentId;
    private final String courseModuleContentHeading;

    private String filePath;

    private final String contentFileUploadDirectory="CourseContentAttachments";

    private String SaveFileAndGetPath(MultipartFile file) throws IOException {
        File directory = new File(String.valueOf(contentFileUploadDirectory));
        if (file.isEmpty()) {
            throw new IOException("file not found");
        }
        if (!directory.exists()) {
            directory.mkdir();
        }
        long currentTimeStamp = Instant.now().toEpochMilli();
        Path path = Paths.get(contentFileUploadDirectory + file.getOriginalFilename() + Long.toString(currentTimeStamp));
        byte[] bytes = file.getBytes();
        Files.write(path, bytes);
        return path.toString();
    }

    private JsonObject GetJsonValue(){
        JsonObject value = Json.createObjectBuilder()
                .add("success", true)
                .add("courseId", getCourseId())
                .add("courseModuleId", getCourseModuleId())
                .add("courseModuleContentId", getCourseModuleContentId())
                .add("courseModuleContentHeading", getCourseModuleContentHeading())
                .add("courseModuleContentText", "")
                .add("courseModuleContentFilePath" , getCourseModuleContentFilePath())
                .build();
        return value;
    }

    FormDataCourseContentView(String courseId, String courseModuleId, String courseModuleContentHeading, MultipartFile courseContentFile) throws IOException {
        this.courseId = courseId;
        this.courseModuleId = Integer.parseInt(courseModuleId);
        this.courseModuleContentHeading = courseModuleContentHeading;
        this.filePath = SaveFileAndGetPath(courseContentFile);
        this.courseModuleContentId = null;

    }

        public Integer getCourseModuleContentId(){
            return courseModuleContentId;
        }

        public String getCourseModuleContentFilePath(){
            return filePath;
        }

        public String getCourseId(){
            return courseId;
        }
        public int getCourseModuleId(){
            return courseModuleId;
        }

        public String getCourseModuleContentHeading() {
            return courseModuleContentHeading;
        }

        public String getCourseModuleContentText(){
            return "";
        }

        public String getSerializedStringForSuccess(){
         return GetJsonValue().toString();
        }

        public String getSerializedStringForFailure(){
            JsonObject value = Json.createObjectBuilder()
                    .add("success", false).build();
            return value.toString();
        }

        public void Update(TextCourseContent textCourseContent){

        }

        public String getSerializedStringForSuccess(String courseId, int courseModuleId, ArrayList<CourseContent> courseContentArrayList){
            return "";
        }



}
