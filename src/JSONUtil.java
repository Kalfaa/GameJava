
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JSONUtil {
    private ObjectMapper _objectMapper;

    JSONUtil(){
        _objectMapper = new ObjectMapper();
    }

    public String convertObjectToStringJSON(Object object){
        ObjectWriter objectWriter = _objectMapper.writer();
        String json ;
        try {
            json = objectWriter.writeValueAsString(object);
        }catch(JsonProcessingException e){
            json = "";
        }
        return json;
    }

    public Object convertStringJSONToObject(String jsonString , Class<?> objectClass)throws IOException{
        return _objectMapper.readValue(jsonString,objectClass);
    }
}
