# What technical debt has been cleaned up?

Show links to a commit where you paid off technical debt. Write 2-5 sentences that explain what debt was paid, and what its classification is.

Enrolment was initially supposed to be implemented in Iteration 2. However, this was eventually pushed to Iteration 3 due to database complications, where we incurred it as Deliberate and Prudent technical debt as a result. It was paid off in Iteration 3 once the complications were resolved. (please english bad)

Commit Link: https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/commit/8d11c4c506b23a449e76d2eca365c7ef2e308789

# What technical debt did you leave?

What one item would you like to fix, and can't? Anything you write will not be marked negatively. Classify this debt.

In the 1st iteration, a prototype database ‘Database.java’ was implemented using ArrayLists, essentially acting as a singleton. Feedback following the iteration noted that the singleton design pattern violates SOLID design principles. In response, a stub database ‘StubDatabase.java’ was implemented in the 2nd iteration by use of Shared Preference. It was also noted that the use of ArrayLists, despite its limitations, was sufficient for the purposes of the stub. As it would have been of little benefit at the time, the choice was made to not pay off this potential debt, leaving the stub to use ArrayLists. It was later realized that, as a result of this choice, a few primary features could not be completed in time.

We classify this debt as Inadvertent and Prudent, as this choice was made deliberately to prioritize system features, but did not understand the hindrance this debt would cause.

# Discuss a Feature or User Story that was cut/re-prioritized

When did you change the priority of a Feature or User Story? Why was it re-prioritized? Provide a link to the Feature or User Story. This can be from any iteration.

The priority of this feature was changed at the end of Iteration 1 to “Low Priority”. It was re-prioritized so that our velocity would remain consistent since Iteration 2 and 3 would require us to implement a number of features. As technical debt began to add up, it became apparent that we may not live up to our overall vision statement. It was more important that we implemented the essential course enrollment features beforehand. All Graduation tracking features will still be considered for the future.

**Feature Link:** https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/9

The priority of this feature was changed at the end of Iteration 2 and moved to Iteration 3. We cut the feature to only include a day view as opposed to a side-by-side week view as the visual implementation of the latter requires an advanced knowledge of layouting and installing Android components. We completed the Timetable feature at the end of Iteration 3.

**Feature Link:** https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/11

# Acceptance test/end-to-end

Write a discussion about an end-to-end test that you wrote. What did you test, how did you set up the test so it was not flaky? Provide a link to that test.

One of the Acceptance tests we wrote is called EnrollTest and it satisfies a user story that allows a student to enroll in a class of their choice. For all our tests, we always start by launching from the Login screen, which is our application’s initial window, and navigate to the activities where the test is performed. Acceptance tests have to be performed in a sequential manner and information is carried over to be used by the following acceptance tests. For EnrollTest, the user logs in with a previously registered username and password in the log in activity, then navigates to the Offered Classes’ department list. Since the cards are generated, the user gets to select their choice of department and course by matching with the component text instead of a component id. The user picks the default section, gets to successfully enroll, and can verify that the enrollment is added through the Transcript view.

**User Story Link:** https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/40

**Enrolltest:** https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/blob/master/app/src/androidTest/java/comp3350/termsetter/AcceptanceTests/EnrollTest.java

# Acceptance test, untestable

What challenges did you face when creating acceptance tests? What was difficult or impossible to test?

Most of our acceptance tests went smoothly and were tested thoroughly but ViewScheduleTest had a bottleneck with verification. We are not able to check the sequence of classes for correction as we had difficulty retrieving recycler elements in that specific activity. Due to this, we wrote the test under the sufficient scope that our user story required - to be able to view all classes at a given day by selecting each day option from the spinner and loading whatever classes are associated with the day at that state.

**User Story Link:** https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/issues/15

**ViewScheduleTest:** https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/blob/master/app/src/androidTest/java/comp3350/termsetter/AcceptanceTests/ViewScheduleTest.java

# Velocity/teamwork

Did your estimates get better or worse through the course? Show some evidence of the estimates/actuals from tasks.  
  
As we progressed through iterations, our estimates became better partly due to the fact that they were more difficult to apply in the beginning. Features in later iterations were reliant on code established in earlier iterations so it became easier to measure how much we could get done between them. In Iteration 1, we severely overestimated our work but under-delivered. In Iteration 2, we tried not to hold our expectations to a similar height as Iteration 1, though we fell short of one feature. Finally, at Iteration 3, we settled the technical debts from Iterations 1 and 2, and reconsidered the scope of our app. We decided early on what features to omit so that we can expect just as sufficiently as we can deliver.

**Velocity graph for cumulative features:**
https://code.cs.umanitoba.ca/3350-winter-2021-a02/group-6/aurora-but-better-a02-group-6/-/blob/master/docs/velocity_graph.png
