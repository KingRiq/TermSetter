# Architecture

Termsetter is designed with a 3-tier architecture, which consists of Presentation Layer, Logic Layer and Persistence Layer. For Iteration 1, the architecture of our system is covered broadly.

## Presentation Layer

**LoginPageActivity**
UI that facilitates student login.

**CreateAccountActivity**
UI that facilitates student account creation.

**MainActivity**
UI that facilitates primary navigation within the system.

**EnrolledClassesViewActivity**
UI that facilitates the enrollment into courses.

***AccountManagement activities***
UIs that facilitate management of various student account elements.
- **AccountManagementMenuActivity**
UI that provides navigation between account management activities.
- **AccountManagementProfileActivity**
UI that facilitates the viewing of student information.
- **AccountManagementChangePasswordActivity**
UI that facilitates the changing of the student password.
- **AccountManagementUpdateEmailActivity**
UI that facilitate the updating of the student email.

***OfferedCourse activities***
UIs that facilitate the viewing of offered courses.
- **OfferedCourseCategoriesActivity**
UI that facilitates the viewing of available course categories.
- **OfferedCourseViewActivity**
- **OfferedCourseDetailsActivity**

## Logic Layer

**AccountValidation**
Class that handles the validation of student information upon creating an account.

**OfferedClassesLogic**
Class that supplies the OfferedClasses activities with information. 

**AccessStudents**
Class that connect account creation, login, and management with associated Persistence objects.

**Services**
Class that retrieves a static instance of the database.

**Enrollment Logic**
Class that handles the enrolling of users into courses.

## Persistence Layer

**DBImporter**
Initializes hscodedb and copies database to device.

**CourseCategories**
The parent class faculty.

**Faculty**
Class the holds a list of offered classes in each faculty.

**UserPersistence**
Interface for handling student account information (updating information).

***HSQLDB Objects***
- **CourseAccess**
Responsible for connecting to hscodedb table called Courses retrieve courses in the database.
- **EnrollAccess**
Responsible for connecting to hscodedb table callled Enrollment, handling the enrollment of users in courses.
- **StudentAccess**
Connects to hscodedb table called Student, responsible for executing sql statements in hscodedb.

## Domain Specific Objects

**StubDatabase**
A stub implementation for storing Users (student).

**User**
The User (student) object.

## Iteration 1 Diagram

Please refer to https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/blob/shadow-master/docs/ARCHITECTURE_DIAGRAM_ITERATION2.PNG






