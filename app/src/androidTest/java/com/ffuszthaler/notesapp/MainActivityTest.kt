package com.ffuszthaler.notesapp


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.newNoteButton), withText("New Note"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.newNoteTitle),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.newNoteBody),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("body test"), closeSoftKeyboard())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.newNoteCategory),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        val materialTextView = onData(anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(`is`("android.widget.PopupWindow$PopupBackgroundView")),
                    0
                )
            )
            .atPosition(1)
        materialTextView.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.newNoteAddButton), withText("Add"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val recyclerView = onView(
            allOf(
                withId(R.id.notesRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    2
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.editNoteTitle), withText("test"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("test2"))

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.editNoteTitle), withText("test2"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(closeSoftKeyboard())

        val appCompatSpinner2 = onView(
            allOf(
                withId(R.id.editNoteCategory),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatSpinner2.perform(click())

        val materialTextView2 = onData(anything())
            .inAdapterView(
                childAtPosition(
                    withClassName(`is`("android.widget.PopupWindow$PopupBackgroundView")),
                    0
                )
            )
            .atPosition(0)
        materialTextView2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.editNoteSaveButton), withText("Save"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val recyclerView2 = onView(
            allOf(
                withId(R.id.notesRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    2
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val materialButton4 = onView(
            allOf(
                withId(R.id.editNoteDeleteButton), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(android.R.id.button2), withText("No"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton5.perform(scrollTo(), click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.editNoteDeleteButton), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.noteFragmentContainerView),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(android.R.id.button1), withText("Yes"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton7.perform(scrollTo(), click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
