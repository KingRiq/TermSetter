package comp3350.termsetter.Persistence.DomainSpecific;

import android.content.Context;
import android.content.SharedPreferences;
import comp3350.termsetter.Persistence.StudentPersistence;
import static android.content.Context.MODE_PRIVATE;


public class StubDatabase implements StudentPersistence {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static int count = 0;
    private static String currentID = null;

    public StubDatabase(Context context, String dbName){
        preferences = context.getApplicationContext().getSharedPreferences(dbName, MODE_PRIVATE);
        editor = preferences.edit();
    }

    public Student insertStudent(Student student){
        String name = student.getName();
        String password = student.getPassword();
        String email = student.getEmailAddress();
        String phoneNumber = student.getPhoneNumber();
        String studentID = student.getStudentID();

        String nameKey = "name" + count;
        String passwordKey = "password" + count;
        String emailKey = "email" + count;
        String phoneKey = "phone" + count;
        String idKey = "id" + count;

        editor.putString(nameKey, name);
        editor.putString(passwordKey, password);
        editor.putString(emailKey, email);
        editor.putString(phoneKey, phoneNumber);
        editor.putString(idKey, studentID);
        editor.apply();

        count += 1;
        return student;
    }

    public void setCurrentStudentID(String sID) {
        this.currentID = sID;
    }

    public int findStudentIndex(String sID) {
        if (this.count <= 0){
            return -1;
        }

        int lookupIndex = 0;
        String id;

        for (int i = 0; i < this.count; i++) {
            id = "id" + i;
            if (preferences.getString(id,"").equals(sID)) {
                lookupIndex = i;
                break;
            }
        }
        return lookupIndex;
    }

    public Student getStudent(String sID) {
        int lookupIndex = findStudentIndex(sID);

        if (lookupIndex != -1) {
            String nameKey = "name" + lookupIndex;
            String passwordKey = "password" + lookupIndex;
            String emailKey = "email" + lookupIndex;
            String phoneKey = "phone" + lookupIndex;
            String idKey = "id" + lookupIndex;

            String name = preferences.getString(nameKey,"");
            String password = preferences.getString(passwordKey,"");
            String email = preferences.getString(emailKey,"");
            String phoneNumber = preferences.getString(phoneKey,"");
            String studentID = preferences.getString(idKey,"");

            return new Student(name, password, email, phoneNumber, studentID);
        }
        else {
            return null;
        }
    }

    public Student getCurrentStudentID() {
        return getStudent(currentID);
    }

    public boolean updatePassword(String newPassword) {
        boolean check = false;
        if (currentID != null) {
            int lookupIndex = findStudentIndex(currentID);
            String passwordKey = "password" + lookupIndex;
            editor.putString(passwordKey, newPassword);
            editor.apply();
            check = true;
        }

        return check;
    }

    public boolean updateEmail(String newEmail) {
        boolean check = false;
        if (currentID != null) {
            int lookupIndex = findStudentIndex(currentID);
            String emailKey = "email" + lookupIndex;
            editor.putString(emailKey, newEmail);
            editor.apply();
            check = true;
        }

        return check;
    }

    public boolean isEmpty() {
        return (count == 0);
    }
}
