package comp3350.termsetter.Tests;


import org.junit.Test;

import comp3350.termsetter.Persistence.DomainSpecific.Student;

import static org.junit.Assert.*;

public class StudentTest {

    String validName = "name";
    String validPasswd = "123pass";
    String validEmail = "mailme@myumanitoba.ca";
    String validPhone = "204";
    String validID = "hohoho";

    @Test
    public void testUserCreate() {
        System.out.println("\nStarting testUserCreate: object exists after creation\n");
        Student student = new Student(validName, validPasswd, validEmail, validPhone, validID);
        assertNotNull(student);
        System.out.println("End testUserCreate: object exists after creation\n");
    }

    @Test
    public void testSetName() {
        System.out.println("\nStarting testSetName: set and return correct value\n");
        Student student = new Student(validName, validPasswd, validEmail, validPhone, validID);
        String newValidName = "barney the dinosaur";
        student.setStudentName(newValidName);
        assertEquals(student.getName(), newValidName);
        System.out.println("End testSetName: set and return correct value\n");
    }

    public void testID() {
        System.out.println("\nStarting testSetName: set and return correct value\n");
        Student student = new Student(validName, validPasswd, validEmail, validPhone, validID);
        assertEquals(student.getStudentID(), validID);
        System.out.println("End testID: return correct value\n");
    }

    @Test
    public void testSetPassword() {
        System.out.println("\nStarting testSetPassword: set and return correct value\n");
        Student student = new Student(validName, validPasswd, validEmail, validPhone, validID);
        String newValidPasswd = "pass123";
        student.setPassword(newValidPasswd);
        assertEquals(student.getPassword(), newValidPasswd);
        System.out.println("End testSetPassword: set and return correct value\n");
    }

    @Test
    public void testSetEmail() {
        System.out.println("\nStarting testSetEmail: set and return correct value\n");
        Student student = new Student(validName, validPasswd, validEmail, validPhone, validID);
        String newValidEmail = "barneyhasmail@myumanitoba.ca";
        student.setEmail(newValidEmail);
        assertEquals(student.getEmailAddress(), newValidEmail);
        System.out.println("End testSetEmail: set and return correct value\n");
    }

    @Test
    public void testGetPhone() {
        System.out.println("\nStarting testGetPhone: return correct value\n");
        Student student = new Student(validName, validPasswd, validEmail, validPhone, validID);
        assertEquals(student.getPhoneNumber(), validPhone);
        System.out.println("End testSetPhone: return correct value\n");
    }

}
