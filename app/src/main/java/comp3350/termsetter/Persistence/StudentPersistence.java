package comp3350.termsetter.Persistence;

import java.sql.SQLException;
import comp3350.termsetter.Persistence.DomainSpecific.Student;

public interface StudentPersistence {

    Student insertStudent(Student student);
           
    Student getStudent(String sID);

    Student getCurrentStudentID();

    boolean isEmpty();

    boolean updatePassword(String password);

    boolean updateEmail(String email);

    void setCurrentStudentID(String inputID);
}
