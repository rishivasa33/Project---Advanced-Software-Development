package dal.csci5308.project.group15.elearning.views.course.courseContent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FetchFileFromPath {

    public static byte[] FetchFileFromPathAsByteArray(String filepath) throws IOException {
        byte[] contents = Files.readAllBytes(Path.of(filepath));
        return contents;
    }
}
