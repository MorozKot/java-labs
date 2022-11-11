package lab2.src.main.java.lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.src.main.java.lab2.registration.model.Instructor;

import java.io.File;
import java.io.IOException;

public class InstructorReader {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static String path = "src/main/resources/";

    /**
     * @return список информации об инструкторе
     */
    public static Instructor[] readInstructorInfo() throws IOException {
        return objectMapper.readValue(new File(path + "instructors.json"), Instructor[].class);
    }

}
