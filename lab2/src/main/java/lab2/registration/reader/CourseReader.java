package lab2.src.main.java.lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.src.main.java.lab2.registration.model.CourseInfo;
import lab2.src.main.java.lab2.registration.model.CourseInstance;

import java.io.File;
import java.io.IOException;

public class CourseReader {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String path = "src/main/resources/";

    /**
     * @return список информации о курсе
     */
    public CourseInfo[] readCourseInfo() throws IOException {
        return objectMapper.readValue(new File(path + "courseInfos.json"), CourseInfo[].class);
    }

    /**
     * @return список студентов-магистров
     */
    public CourseInstance[] readCourseInstance() throws IOException {
        return objectMapper.readValue(new File(path + "courseInstances.json"), CourseInstance[].class);
    }
}
