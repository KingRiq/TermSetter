package comp3350.termsetter.Persistence.DomainSpecific;

import java.sql.SQLException;
import java.util.List;


public interface EnrollPersistence {

    public List<String> getStudentEnrollment(String studentID);
    void enroll(String sID, String section, String cID);
    void unenroll(String sID, String section, String cID);
}
