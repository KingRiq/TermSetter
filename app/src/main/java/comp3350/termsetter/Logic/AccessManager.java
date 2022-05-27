package comp3350.termsetter.Logic;

import android.content.Context;
import java.io.Serializable;
import java.util.List;

import comp3350.termsetter.Persistence.DomainSpecific.CoursePersistence;
import comp3350.termsetter.Persistence.DomainSpecific.EnrollPersistence;
import comp3350.termsetter.Persistence.DomainSpecific.Student;
import comp3350.termsetter.Persistence.StudentPersistence;

public class AccessManager implements Serializable
{
    private StudentPersistence studentPersistence;
    private CoursePersistence coursePersistence;
    private EnrollPersistence enrollPersistence;
    private List<Student> students;
    private Student student;
    private int currentStudent;


    public AccessManager() {
        studentPersistence = Services.getRealStudentAccess();
        coursePersistence = Services.getCoursePersistence();
        enrollPersistence = Services.getEnrollPersistence();

        students = null;
        student = null;
        currentStudent = 0;
    }


    public AccessManager(Context context) {
        studentPersistence = Services.getFakeStudentAccess(context);
        students = null;
        student = null;
        currentStudent = 0;
    }

    public AccessManager(final Student student) {
        this();
        this.insertStudent(student);
    }

    public StudentPersistence getStudentPersistence() {
        return studentPersistence;
    }

    public Student insertStudent(Student currentStudent) {
        return studentPersistence.insertStudent(currentStudent);
    }

    public Student getStudent(String student_id) {
        return studentPersistence.getStudent(student_id);
    }

}
