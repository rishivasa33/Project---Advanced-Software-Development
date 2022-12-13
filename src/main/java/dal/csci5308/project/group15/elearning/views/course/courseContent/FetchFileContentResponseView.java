package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class FetchFileContentResponseView {

   private FileCourseContent fileCourseContent;


    FetchFileContentResponseView(FileCourseContent fileCourseContent){
        this.fileCourseContent = fileCourseContent;
    }

    public  ResponseEntity<byte[]> GetResponseForSuccess() {
        try {
            if(fileCourseContent == null){
                return GetResponseForFailure();
            }
            byte[] fileByteArray = FetchFileFromPath.FetchFileFromPathAsByteArray(fileCourseContent.GetFilePath());
            HttpHeaders headers = new HttpHeaders();
            String filename = fileCourseContent.GetFileName();
            System.out.println("filename : " + filename);
            headers.setContentDispositionFormData(filename,filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(fileByteArray, headers, HttpStatus.OK);
            return response;
        }
        catch (IOException exception){
            return GetResponseForFailure();
        }
    }

    public static ResponseEntity<byte[]> GetResponseForFailure(){
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
