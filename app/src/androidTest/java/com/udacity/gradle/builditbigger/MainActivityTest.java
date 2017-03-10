package com.udacity.gradle.builditbigger;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Created by Fabin Paul, Eous Solutions Delivery on 3/10/2017 10:48 AM.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testIfJokeDisplayed() throws Throwable {
        onView(withText(mActivityRule.getActivity().getString(R.string.button_text))).perform(click());
        try {
            onView(withContentDescription("Interstitial close button")).check(matches(isDisplayed()));
            onView(withContentDescription("Interstitial close button")).perform(click());
        }catch (NoMatchingViewException e) {

        }
        onView(withId(R.id.jokes_txtvw)).check(matches(withText(not(isEmptyOrNullString()))));
        onView(withId(R.id.jokes_txtvw)).check(matches(withText(not(mActivityRule.getActivity().getString(R.string.no_jokes_avail)))));
    }
}