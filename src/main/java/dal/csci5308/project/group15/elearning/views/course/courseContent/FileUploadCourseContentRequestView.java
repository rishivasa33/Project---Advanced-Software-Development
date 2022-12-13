package dal.csci5308.project.group15.elearning.views.course.courseContent;

import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;


public class FileUploadCourseContentRequestView implements CourseContentRequestView {

    private final String courseId;
    private final int courseModuleId;

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
        Path path = Paths.get(contentFileUploadDirectory + "/" +  Long.toString(currentTimeStamp) + '.' + file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        Files.write(path, bytes);
        return path.toString();
    }



    FileUploadCourseContentRequestView(String courseId, String courseModuleId, String courseModuleContentHeading, MultipartFile courseContentFile) throws IOException {
        this.courseId = courseId;
        this.courseModuleId = Integer.parseInt(courseModuleId);
        this.courseModuleContentHeading = courseModuleContentHeading;
        this.filePath = SaveFileAndGetPath(courseContentFile);

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



    public String getFilePath(){
        return filePath;
    }



}
