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

**Timetable/"View My Schedule"**
UI that displays the student's current course enrollment per weekday.

**Transcript/"View My Classes"**
UI that displays the student's current enrollment in all courses.

***OfferedCourse activities***
UIs that facilitate the viewing of offered courses.
- **OfferedCourseCategoriesActivity**
UI that displays available departments for course enrollment.
- **OfferedCourseViewActivity**
UI that displays available courses for each department.
- **OfferedCourseDetailsActivity**
UI that displays 
**Unenrollment**
UI that facilitates the unenrollment in any currently enrolled courses.

## Logic Layer

**AccessManager**
A class that serves as an access switch, allowing the hdqldb to toggle between different Logic layers. 

**AccountValidation**
Class that handles the validation of student information upon creating an account.

**Enrollment Logic**
Class that handles the enrolling of users into courses.

**OfferedClassesLogic**
Class that supplies the OfferedClasses activities with information. 

**Services**
Class that retrieves a static instance of the database.



## Persistence Layer

***CourseCategories***
The parent class faculty.
-**CourseCategoryDriver**
Loads contents into StubDatabase from a raw file.
-**CourseCategoryPersistence**
Interface between OfferedClassLogic and the Faculty class.
-**CourseCategorySQLDriver**
Enables the retrieval of hsqldb records for the AccessManager.

**CourseOffering**
Describes primary keys for database record retrieval.

**CourseSection**
Describes primary keys for database record retrival.

**DBImporter**
Initializes hscodedb and copies database to a selected device.

**Faculty**
Class the holds a list of offered classes in each faculty.

**Main**
Class that handles the importing of a specified database.

**StudentPersistence**
Interface for managing student account information.


## Domain Specific Objects

**CoursePersistence**
Interface used by the AccessManager to connect to CourseAccess.

**EnrollPersistence**
Interfaces used by the AccessManager to connect to EnrollAccess.

**StubDatabase**
Stub database implementation used to store Students.

**Student**
The Student object.

***HSQLDB Objects***
- **CourseAccess**
Connects calling class to hscodedb table 'Courses' to retrieve courses in the database.
- **EnrollAccess**
Connects calling class to hscodedb table 'Enrollment', handling the enrollment of users in courses.
- **StudentAccess**
Connects calling class to hscodedb table 'Student', responsible for executing sql statements in hscodedb.

## Iteration 3 Diagram

Please refer to https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/blob/shadow-master/docs/ARCHITECTURE_DIAGRAM_ITERATION2.PNG






