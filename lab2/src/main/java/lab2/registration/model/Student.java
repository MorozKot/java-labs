package lab2.src.main.java.lab2.registration.model;

import java.util.Arrays;

/**
 * Класс для информации о студенте
 */
public class Student extends Person {

    /**
     * список идентификаторов курсов (CourseInstance.id), пройденных студентом
     */
    private long[] completedCourses;

    /**
     * список идентификаторов курсов (CourseInstance.id), которые студент проходит на данный момент
     */
    private long[] currentCourses;

    // TODO: добавить геттеры и сеттеры
    public long[] getCompletedCourses() {
        return completedCourses;
    }

    public long[] getCurrentCourses() {
        return currentCourses;
    }

    public void setCompletedCourses(long[] completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void setCurrentCourses(long[] currentCourses) {
        this.currentCourses = currentCourses;
    }

    @Override
    public String toString() {
        return super.toString()+"\ncurrentCourses: "+Arrays.toString(currentCourses)+"\ncompletedCourses: "+Arrays.toString(completedCourses)+"\n";
    }
}
