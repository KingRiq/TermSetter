# Architecture

Termsetter is designed with a 3-tier architecture, which consists of Presentation Layer, Logic Layer and Persistence Layer. For Iteration 1, the architecture of our system is covered broadly.

# Presentation Layer

LoginPageActivity
- UI for login into the system. Also, the main page of the program

CreateAccountActivity
- UI for creating a new account

MainActivity
- UI for logout and

AccountManagementMenuActivity
- UI for managing profile (chaning email and password)

CourseCategoriesActivity
- UI for exploring what courses are offered

# Logic Layer

AccountChangePassword
- Class that is responsible for changing old password to new password

AccountUpdateEmail
- Class that is responsible for chaning old email to new email

LoginPage
- Class that is responsible for sign in to Termsetter

CreateAccount
- Class that is responsible for creating a new account

# Persistence Layer

CourseCategories
- Interface for courses offered

Database
- Interface for Users

User
- Interface for storing student profiles (ID, password, Email and Phone number)


# Domain Specific Objects

Database
- Database object that contains User object (only 1 User object is stored in Iteration 1)

User
- User object

# Iteration 1 Diagram

Please refer to [Link]






