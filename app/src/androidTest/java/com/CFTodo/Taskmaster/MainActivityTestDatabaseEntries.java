package com.CFTodo.Taskmaster;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestDatabaseEntries {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTestDatabaseEntries() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.MainActivityAddTskBtn), withText("ADD TASK"),
                        childAtPosition(
                                allOf(withId(R.id.MainActivityimg),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.AddTextActivityTextViewInputTask), withText("My Task"),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(longClick());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.AddTextActivityTextViewInputTask), withText("My Task"),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("Clean"));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.AddTextActivityTextViewInputTask), withText("Clean"),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.AddTextActivityTextViewInputEntry), withText("Do Something"),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("Clean the house."));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.AddTextActivityTextViewInputEntry), withText("Clean the house."),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.AddTaskViewSpinner),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                5),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        materialTextView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.AddTextActivityTextViewAddTaskBTN), withText("ADD TASK"),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.FragTextTaskTitle), withText("Clean"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.MainActivityTaskRecyclerVIew)))),
                        isDisplayed()));
        textView.check(matches(withText("Clean")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.MainActivityTaskRecyclerVIew),
                        childAtPosition(
                                withId(R.id.MainActivityimg),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.TaskDetailActivityTitleTextView), withText("Clean"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView2.check(matches(withText("Clean")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.TaskDetailActivityTaskDecriptionTextView), withText("Clean the house."),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView3.check(matches(withText("Clean the house.")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.TaskDetailActivityTaskDecriptionTextView), withText("Clean the house."),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView4.check(matches(withText("Clean the house.")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.TaskDetailTextViewTaskStatus), withText("In Progress"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView5.check(matches(withText("In Progress")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
