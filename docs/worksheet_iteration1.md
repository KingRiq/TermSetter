5 point RUBRIC
All questions answered fully, meaningfully.
Submission is void of spelling mistakes and grammar errors
Worksheet is formatted well, easily available


Iteration 1 Worksheet
=====================

Adding a feature
-----------------

Tell the story of how one of your features was added to the project.
Provide links to the feature, student stories, and merge requests (if used), associated tests, and merge commit that was used to complete the feature. Use one or two paragraphs which can have point-form within them.

Communication has been the key factor behind the group managing to work towards a goal of bringing this app to life. Once the product was broken down into features and student stories, it became easier for the team members to visualize their individual strengths and step up to take matters into their own hands. Development tasks were decided that would make the features functional. The team members assigned to them created branches on GitLab, and went to work. The following list describes each of the four features, who was behind it, and the links to the merge commits and any associated tests:
Log into Account: Allow a student to use the app after they have entered an email and password associated with the account.
Story: Kevin implemented the interface for the Login activity, which allows the student to land on the TempNavigationLinks Activity if successful. A method on the logic layer was written to consult the student database for validation by Tuan.
Link: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/2
User Story: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/14
Test: TBD
Merge request: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/merge_requests/19
Satisfying merge commit: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/ce1759896b1d6fff7dafac5bd1324caa2f98a3d8
Display/Update Account Information: A display for the student’s name, email, program, and password appearing as a series of dots. The student can change any of the given information after entering their password.
Story: Mika created the interface that allows account management. Tuan implemented the validation method that is consulted upon changing the password.
Link: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/3
User Story: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/16
Test: TBD
Merge request: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/merge_requests/11
Satisfying merge commit: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/c08966bc95e5b7de06f1f49cade43045cd173e33
Course Categorization: Allow a student to see classes under categories. Students can see the classes they are taking, which includes the class name, section, instructor, class time, and cost.
Story: The interface is created by Andrea for displaying class categorizations, class offerings and class detail. Eriq implemented the database that contains information on all categories and offered classes. Both worked on enabling the logical communication between the presentation and persistence in these Activities.
Link: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/4
User Story: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/20
Test: TBD
Merge request: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/merge_requests/24
Satisfying merge commit: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/0094a74995c0ad89c76d87cf4c75cd49f6ea8500
Creating a Private Account: Creation of a student account after getting the name, password, email, and program.
Story: Andrea created the User class which enables the creation of student accounts. The creation of student accounts is prompted from the CreateAccount activity.
Link: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/1
User Story: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/13
Test: TBD
Merge request: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/merge_requests/19
Satisfying merge commit: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/ce1759896b1d6fff7dafac5bd1324caa2f98a3d8
Creating a Database: Creation of a fake database to store users’ information.
Story: Tuan and Eriq implement the Database Class and its associate function to initialize/add users to the database as well as passing the database through other activities.
Link:
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/25
User Story:
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/14
Test: TBD
Merge request:
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/merge_requests/16
Satisfying merge commit: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/0b6f73cfec90e304febdd1f4c3ff89c4f8b7eae6



Clone this url, but the problem is you can’t see the commits. But if you get the code, you can point out a violation to me, and I’ll find the commit
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-7/gaming-hub.git

Yup. Lets see if I can take the route

Exceptional code - TBD Andrea
----------------

Provide a link to a test of exceptional code. In a few sentences,
provide an explanation of why the exception is handled or thrown
in the code you are testing.

Branching
----------

Provide a link to where you describe your branching strategy.
(I wrote a branching strategy.md file for this which we can add to gitlab, and then link that to our readme.md)
Kevin: BRANCH_STRATEGY.md is available on README.md


Provide screen shot of a feature being added using your branching strategy
successfully. The [GitLab Graph tool can do this](https://code.cs.umanitoba.ca/comp3350-summer2019/cook-eBook/-/network/develop),
as well as using `git log --graph`.






SOLID
-----

Find a SOLID violation in the project of group `(n%12)+1` (group 12 does group 1).
Open an issue in their project with the violation,
clearly explaining the SOLID violation - specifying the type, provide a link to that issue. Be sure
your links in the issues are to **specific commits** (not to `main`, or `develop` as those will be changed).

Provide a link to the issue you created here.
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-7/gaming-hub/-/issues/53



Agile Planning
--------------

Write a paragraph about any plans that were changed. Did you
'push' any features to iteration 2? Did you change the description
of any Features or User Stories? Have links to any changed or pushed Features
or User Stories.

We did not push any Features or User Stories to future iterations, as we ended up finishing all four features which we designated for Iteration 1. However, we did refactor the descriptions of two of our features based on what will be more logical to implement now.
For the Private Account feature, we decided to not include the student’s choice of program as they create their account and we decided that it would be an option that we will implement in Iteration 3 along with the Graduation tracker feature. We, however, included phone numbers as one of the required fields in student account creation.
(Link: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/1)

For the class categorization feature, we did not include the class cost within the class details anymore as we decided that all classes will have a fixed price, which we will aggregate based on the number of a student’s courses to be displayed on the Tuition Breakdown feature in Iteration 2.
Link: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/4

I think 1 or 2 more things got pushed to iteration 2. I saw Eriq mention passwordchange was pushed for sure
