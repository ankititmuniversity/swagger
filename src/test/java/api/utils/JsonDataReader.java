package api.utils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import api.payloads.User;

import java.io.File;
import java.util.List;

public class JsonDataReader {
    public static List<User> getUsers() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
            new File("src/test/resources/jsonData/user_payload.json"),
            new TypeReference<List<User>>() {}
        );
    }
}

