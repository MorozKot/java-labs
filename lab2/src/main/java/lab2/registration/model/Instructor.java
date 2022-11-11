package lab2.src.main.java.lab2.registration.model;

import java.util.Arrays;

/**
 * Класс для информации о преподавателе
 */
public class Instructor extends Person {

    /**
     * Идентификаторы курсов, которые может вести преподаватель
     */
    int[] canTeach;

    // TODO: добавить геттеры и сеттеры

    public int[] getCanTeach() {
        return canTeach;
    }

    public void setCanTeach(int[] canTeach) {
        this.canTeach = canTeach;
    }

    @Override
    public String toString() {
        return super.toString()+"\ncanTeach: "+ Arrays.toString(canTeach)+"\n";
    }
}
