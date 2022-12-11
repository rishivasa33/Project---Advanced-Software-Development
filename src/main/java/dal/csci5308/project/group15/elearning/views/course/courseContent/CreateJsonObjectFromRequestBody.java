package dal.csci5308.project.group15.elearning.views.course.courseContent;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringReader;

public class CreateJsonObjectFromRequestBody {

    public  static JsonObject GetJsonObjectFromRequestBody(String requestBody){
        try (var stringReader = new StringReader(requestBody)){
            var jsonObject = Json.createReader(stringReader).readObject();
            return jsonObject;
        }
    }
}
