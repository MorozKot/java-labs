package lab2.src.main.java.lab2.registration.model;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Класс с данными о проведении курса. Один курс (например, дискретная математика) может быть проведен несколько раз.
 */
public class CourseInstance {
    
    /**
     * идентификатор проведения курса
     */
    private long id;

    /**
     * идентификатор курса, соответствующий CourseInfo.id
     */
    private long courseId;

    /**
     * идентификатор преподавателя
     */
    private long instructorId;

    /**
     * дата начала курса
     */
    private LocalDate startDate;

    /**
     * ограничение на число студентов курса
     */
    private int capacity = -1;

    // TODO: добавить геттеры и сеттеры


    public void setId(long id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate);
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public long getId() {
        return id;
    }

    public long getCourseId() {
        return courseId;
    }

    public int getCapacity() {
        return capacity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public String toString() {
        return "id: "+id+"\ncapacity: "+capacity+"\ncourseId: "+courseId+"\ninstructorId: "+instructorId+"\nstartDate: "+startDate+"\n";
    }
}
