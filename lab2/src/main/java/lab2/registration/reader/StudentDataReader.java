package lab2.src.main.java.lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.src.main.java.lab2.registration.model.Student;

import java.io.File;
import java.io.IOException;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();
    private String path="src/main/resources/";

    /**
     * @return список студентов-бакалавров
     */
    public Student[] readBachelorStudentData() throws IOException {
        return objectMapper.readValue(new File(path+"bachelorStudents.json"), Student[].class);
    }

    /**
     * @return список студентов-магистров
     */
    public Student[] readMasterStudentData() throws IOException {
        return objectMapper.readValue(new File(path+"masterStudents.json"), Student[].class);
    }

}
