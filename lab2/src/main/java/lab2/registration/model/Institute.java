package lab2.src.main.java.lab2.registration.model;


import lab2.src.main.java.lab2.registration.reader.CourseReader;
import lab2.src.main.java.lab2.registration.reader.InstructorReader;
import lab2.src.main.java.lab2.registration.reader.StudentDataReader;
import lab2.src.main.java.lab2.registration.service.CourseInstructorService;
import lab2.src.main.java.lab2.registration.service.StudentService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

public class Institute implements CourseInstructorService, StudentService {
    private int numberOfStudents;
    private Student[] masters;
    private Student[] bachelors;
    private CourseInfo[] courseInfos;
    private CourseInstance[] courseInstances;
    private Instructor[] instructors;

    public Institute(StudentDataReader sdr, CourseReader cr) throws IOException {
        this.bachelors = sdr.readBachelorStudentData();
        this.masters = sdr.readMasterStudentData();
        this.courseInfos = cr.readCourseInfo();
        this.courseInstances = cr.readCourseInstance();
        this.instructors = InstructorReader.readInstructorInfo();
        this.numberOfStudents = bachelors.length + masters.length;
    }

    public static void main(String[] args) throws IOException {
        Institute inst = new Institute(new StudentDataReader(), new CourseReader());
    }

    @Override
    public Student[] findStudentsByCourseId(long courseId) {
        Student[] result = new Student[this.numberOfStudents];
        int count = 0;
        for (int i = 0; i < bachelors.length; i++) {
            if (bachelors[i].getCurrentCourses() != null) {
                for (int j = 0; j < bachelors[i].getCurrentCourses().length; j++) {
                    long temp = bachelors[i].getCurrentCourses()[j];
                    if (courseId == temp) {
                        result[count++] = bachelors[i];
                    }
                }
            }
        }
        for (int i = 0; i < masters.length; i++) {
            if (masters[i].getCurrentCourses() != null) {
                for (int j = 0; j < bachelors[i].getCurrentCourses().length; j++) {
                    long temp = bachelors[i].getCurrentCourses()[j];
                    if (courseId == temp) {
                        result[count++] = bachelors[i];
                    }
                }
            }
        }
        result = (Student[]) this.cutter(result, count);
        return result;
    }

    @Override
    public Student[] findStudentsByInstructorId(long instructorId) {
        Instructor instructor = findInstructorById(instructorId);
        int[] courses;
        Student[] students = null;
        if (instructor != null) {
            courses = instructor.getCanTeach();
            students = new Student[numberOfStudents * courses.length];
            int count = 0;
            for (int i = 0; i < courses.length; i++) {
                Student[] temp = this.findStudentsByCourseId(courses[i]);
                for (int j = 0; j < temp.length; j++) {
                    students[count++] = temp[j];
                }
            }
            students = (Student[]) this.cutter(students, count);
            students = this.deleteDublicates(students);
        }
        return students;
    }

    @Override
    public Instructor[] findReplacement(long instructorId, long courseId) {
        Instructor[] result = new Instructor[this.instructors.length];
        int count = 0;
        for (int i = 0; i < result.length; i++) {
            if (instructorId != instructors[i].getId()) {
                int[] courses = instructors[i].getCanTeach();
                for (int j = 0; j < courses.length; j++) {
                    if (courses[j] == courseId) {
                        result[count++] = instructors[i];
                    }
                }
            }
        }
        result = (Instructor[]) this.cutter(result, count);
        return result;
    }


    @Override
    public ActionStatus subscribe(long studentId, long courseId) {
        ActionStatus result = ActionStatus.NOK;
        Student stud = this.findStudentById(studentId);
        CourseInfo ci = findCourseInfoById(courseId);
        CourseInstance instance = findCourseInstanceById(courseId);
        if (stud != null && ci != null && instance != null) {
            if (instance.getStartDate().isAfter(LocalDate.now()) && this.lookCategory(ci.getStudentCategories(), this.isMaster(stud))
                    && lookPrerequisites(stud, ci) && instance.getCapacity() > -1) {
                instance.setCapacity(instance.getCapacity() - 1);
                this.writeCourseForStud(stud, ci.getId());
                result = ActionStatus.OK;
            }
        }
        return result;
    }


    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        ActionStatus result = ActionStatus.NOK;
        Student stud = this.findStudentById(studentId);
        CourseInstance instance = this.findCourseInstanceById(courseId);
        if (stud != null && instance != null) {
            if (instance.getStartDate().isAfter(LocalDate.now())) {
                this.unWriteCourseForStud(stud, instance.getId());
                result = ActionStatus.OK;
            }
        }
        return result;
    }


    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
        CourseInstance[] result = null;
        Student stud = this.findStudentById(studentId);
        if (stud != null) {
            long[] subs = stud.getCurrentCourses();
            result = new CourseInstance[subs.length];
            for (int i = 0; i < subs.length; i++) {
                result[i] = this.findCourseInstanceById(subs[i]);
            }
        }
        return result;
    }


    private Person[] cutter(Person[] result, int count) {
        Person[] newResult = new Student[count];
        for (int i = 0; i < count; i++) {
            newResult[i] = result[i];
        }
        return newResult;
    }

    private Instructor findInstructorById(long instructorId) {
        Instructor result = null;
        for (int i = 0; i < this.instructors.length; i++) {
            if (instructors[i].getId() == instructorId) {
                result = instructors[i];
                break;
            }
        }
        return result;
    }

    private Student[] deleteDublicates(Student[] students) {
        HashSet<Student> temp = new HashSet<>();
        for (int i = 0; i < students.length; i++) {
            temp.add(students[i]);
        }
        Student[] result = temp.toArray(new Student[temp.size()]);
        return result;
    }

    private Student findStudentById(long studentId) {
        Student result = null;
        for (int i = 0; i < bachelors.length; i++) {
            if (bachelors[i].getId() == studentId) {
                result = bachelors[i];
                break;
            }
        }
        if (result == null) {
            for (int i = 0; i < masters.length; i++) {
                if (masters[i].getId() == studentId) {
                    result = masters[i];
                    break;
                }
            }
        }
        return result;
    }


    private CourseInfo findCourseInfoById(long courseId) {
        CourseInfo result = null;
        for (int i = 0; i < courseInfos.length; i++) {
            if (courseInfos[i].getId() == courseId) {
                result = courseInfos[i];
                break;
            }
        }
        return result;
    }


    private CourseInstance findCourseInstanceById(long courseId) {
        CourseInstance result = null;
        for (int i = 0; i < courseInstances.length; i++) {
            if (courseInstances[i].getCourseId() == courseId) {
                result = courseInstances[i];
                break;
            }
        }
        return result;
    }


    private boolean isMaster(Student stud) {
        boolean isMaster = false;
        for (int i = 0; i < masters.length; i++) {
            if (stud.equals(masters[i])) {
                isMaster = true;
                break;
            }
        }
        return isMaster;
    }


    private boolean lookCategory(StudentCategory[] studentCategories, boolean isMaster) {
        boolean result = false;
        if (studentCategories.length == 2) {
            result = true;
        } else {
            if ((studentCategories[0].equals(StudentCategory.MASTER) && isMaster) || (studentCategories[0].equals(StudentCategory.BACHELOR) && !isMaster)) {
                result = true;
            }
        }
        return result;
    }


    private boolean lookPrerequisites(Student stud, CourseInfo ci) {
        long[] completed = stud.getCompletedCourses();
        long[] requisites = ci.getPrerequisites();
        boolean isHave = true;
        for (int i = 0; i < completed.length; i++) {
            isHave = false;
            for (int j = 0; j < requisites.length; j++) {
                if (completed[i] == requisites[j]) {
                    isHave = true;
                    break;
                }
            }
            if (!isHave) {
                break;
            }
        }
        return isHave;
    }


    private void writeCourseForStud(Student stud, long id) {
        long[] current = stud.getCurrentCourses();
        long[] newCurrect = new long[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            newCurrect[i] = current[i];
        }
        newCurrect[current.length] = id;
        stud.setCurrentCourses(newCurrect);
    }


    private void unWriteCourseForStud(Student stud, long id) {
        long[] current = stud.getCurrentCourses();
        long[] newCurrent = new long[current.length - 1];
        int count = 0;
        for (int i = 0; i < current.length; i++) {
            if (current[i] != id) {
                newCurrent[count++] = current[i];
            }
        }
        stud.setCurrentCourses(newCurrent);
    }
}
