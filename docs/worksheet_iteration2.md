# Iteration 2 Worksheet
=====================

## **Paying off technical debt**
-----------------

A technical debt that we were supposed to handle last Iteration was the organization of the java classes into their proper layers. Many logic and persistence functions were left inside Activity classes, while many java classes were placed in incorrect packages that still used the default com.example prefix. These instances of technical debt were handled in the commits:

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/ea5ae60f62dd8682ac5da4344ad1787755fb3150 
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/41a1b4ccb0942f4fdbfaf2213aea43316a81c6da

We classify this technical debt as Reckless, because we started writing implementation early without giving thought to package separation, and Inadvertent, as we were unaware of this mistake and unable to resolve the issue prior to release.
Another instance of technical debt that was encountered in the implementation of the User stub database. In Iteration 1, our User database stored a list of users, but was only able to return the latest created student. The User information that was used to log in was not reflected in profile viewing activity AccountManagementProfile, which continued to display default fake User information. Other account management activity methods that attempt to change the User’s password or email did not overwrite the original password or email in the UserAccount. This technical debt was handled in the commits:

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/merge_requests/48/diffs

This technical debt is classified as Reckless, because we failed to account for the time needed to implement these functions, and Inadvertent, as this too was a mistake that we were unaware of, and thus unable to implement prior to release.

## **SOLID**
----------------

This project contains a violation of the Liskov Substitution Principle in PlayerData.java. IPlayer defines an interface with a method that returns a List of players based only on a team name, but the PlayerData.java returns allPlayer that contains the list of all players. PlayerDataHSQLDB.java returns a list based on team name so the results will be ambiguous.

https://code.cs.umanitoba.ca/3350-winter-2021-a03/winnipeg-sports-app-a03-group-6/-/issues/63

## **Retrospective**
----------

The past Iteration left our group with many lessons. One of the biggest challenges we encountered as a team was the surplus of merging conflicts. At the last minute, we realized that the information across our branches contained multiple conflicts. Deciding which changes needed to be kept became a long, tedious process. These conflicts were often the result of branches being based on more recent implementations than others, such as older versions that contained insightful comments, versions with code modified to fit a test. We learned that we must notify team members of what we are working on or changing, and when we are going to merge a branch into our shadow-master. 

Merge requests (https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/merge_requests?scope=all&utf8=%E2%9C%93&state=all) became a more frequent practice this Iteration. We needed to be less independent, working more closely with each other and pushing changes more frequently. This Iteration, we implemented an informal ‘Buddy System’, encouraging two or more team members to collaborate on one or more related tasks, often within the same branch. For example, buddies Tuan (Commit: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/6d43b6d168a2431332bd4997a00d345c15c277e3), and Eriq (Commit: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/868b0007b54cb778e0519df783b2137506b49986 ) worked on the same DB-TEST branch, moving towards integrating new persistence classes for general app use. 

We learned we must approach the merging of our branches earlier than later. We noted that issues with branching can be mitigated by working in advance so that enough time will be allocated to each phase. In the previous iteration, logical implementation and tests  were written in a simultaneous timeframe and, as a result, neither sufficiently covered the scope of the tasks by the iteration deadline. This Iteration, we performed our major merges early as demonstrated in the following links:
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/96a27dc3794afc5d9747e2be6f3aa61e5ba30f1a 
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/3dac105a3fba54ee1f6ddad6133bd5067a0a0178 
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/183276b83335be0156658c88ce58ecab2201a8ac 
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/641857094cf5adde9dc21b7cdb0643ba2601520d 
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/8e8a2acec518eec21e7df4626f80c8ab4e239c65 

## **Design patterns**
-----

A design pattern that is present in our project is the use of the Adapter. We have three views that each implement a unique RecyclerViewAdapter as packaged in https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/tree/master/app/src/main/java/comp3350/termsetter/UIAdapters. 

RecyclerView objects on their own cannot implement “recycling” functionality, such as the reuse of cards, as Android widgets are usually static elements constrained to one appearance defined by the xml file associated with its java activity. However, a Recycler adapter will enable this functionality by implementing the method definitions of a ViewHolder. This ViewHolder is a class that cannot be used by the RecyclerView as is, but can be created by any RecyclerView.Adapter instance. The ViewHolder has an interface that enables code to be executed upon creation or binding to an element. The ViewHolder can take another xml layout and reuse it as many times as defined by the Recycler.Adapter. 

Using these classes, the adapter can pass information from RecyclerView to the recycled layout to and fro. For example, the RecyclerView can define a unique property, such as card classes,  that will then manifest in the recycled layout. The Adapter can also set unique event handlers on individual recycled elements by passing the instance of their “view” to the Recycler. Then, with the adapter equipped, the Recycler will procedurally assign a handler to the view. 

## **Iteration 1 Feedback Fixes**
--------------

https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/30

Our marker identified 4 issues from the previous iteration, highlighting missing or faulty functionality in our app. The first issue noted insufficiently helpful or absent notification messages provided to the student when attempting to create an account with invalid input. We resolved this issue by prompting message toasts that inform the student of where they have supplied invalid input and how these inputs can be corrected.

The second issue noted the lack of formatting standards enforced in input fields, such as the phone accepting entries that contain letters. We fixed the issue by implementing account validation logic that will verify more complex patterns in email, phone number, name, and password when supplying new student information.

The third and fourth issues concerned the sudden termination of the program when updating the password or email after a simulated class enrollment. We discovered that pressing the back button in an intent hierarchy loads a fresh instance of the parent activity and will lose its original bundles. This makes the users susceptible to sending a null pointer reference to the account management methods. We disabled intent hierarchy altogether, relying on the application services to retain the logged in student information.