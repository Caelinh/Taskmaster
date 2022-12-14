package com.CFTodo.Taskmaster;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
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

import com.CFTodo.Taskmaster.Activities.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addActivityTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.MainActivityUsrSettingsBtn), withText("User Settings"),
                        childAtPosition(
                                allOf(withId(R.id.MainActivityimg),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.UserSettingsEditUsername),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Username"), closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.UserSettingsSaveBtn), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.mainActivityTextView), withText("Username's Tasks"),
                        withParent(allOf(withId(R.id.MainActivityimg),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        textView.check(matches(withText("Username's Tasks")));

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.MainActivityAddTskBtn), withText("ADD TASK"),
                        childAtPosition(
                                allOf(withId(R.id.MainActivityimg),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.AddTextActivityTextViewInputTask),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("DO the dishes"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.AddTextActivityTextViewInputEntry),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("get them done"), closeSoftKeyboard());

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

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("In_Progress"),
                        withParent(allOf(withId(R.id.AddTaskViewSpinner),
                                withParent(withId(R.id.AddTaskActivityAddTaskBtn)))),
                        isDisplayed()));
        textView2.check(matches(withText("In_Progress")));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.AddTextActivityTextViewAddTaskBTN), withText("ADD TASK"),
                        childAtPosition(
                                allOf(withId(R.id.AddTaskActivityAddTaskBtn),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.FragTextTaskTitle), withText("DO the dishes"),
                        withParent(allOf(withId(R.id.frameLayout),
                                withParent(withId(R.id.MainActivityTaskRecyclerVIew)))),
                        isDisplayed()));
        textView3.check(matches(withText("DO the dishes")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.MainActivityTaskRecyclerVIew),
                        childAtPosition(
                                withId(R.id.MainActivityimg),
                                4)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.TaskDetailActivityTaskDecriptionTextView), withText("get them done"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView4.check(matches(withText("get them done")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.TaskDetailTextViewTaskStatus), withText("In_Progress"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        textView5.check(matches(withText("In_Progress")));
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
