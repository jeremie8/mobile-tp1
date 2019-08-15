package ca.csf.mobile.tp1;

import android.content.Context;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.InstrumentationRegistry;

import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MessagesTests {

    @Rule
    public ActivityTestRule<Vue> mActivityRule = new ActivityTestRule<>(Vue.class);

    @Test
    public void useAppContext()
    {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ca.csf.mobile.tp1", appContext.getPackageName());
    }

    @Test
    public void testMessageOK() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("H2O"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.msgOK)));
    }

    @Test
    public void testMessage0() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText(""), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur0)));
    }

    @Test
    public void testMessage1() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("12"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur1)));
    }

    @Test
    public void testMessage2() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("Zz"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur2)));
    }

    @Test
    public void testMessage3() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("O(2H)"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur3)));
    }

    @Test
    public void testMessage4() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("H1"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur4)));
    }

    @Test
    public void testMessage5() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("H03"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur5)));
    }

    @Test
    public void testMessage6() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("H2()"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur6)));
    }

    @Test
    public void testMessage7() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("H2)"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur7)));
    }

    @Test
    public void testMessage8() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("H2("), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur8)));
    }

    @Test
    public void testMessage9() throws Exception {
        onView(withId(R.id.btnNettoyer)).perform(click());
        onView(withId(R.id.txtInput)).perform(typeText("eH2"), closeSoftKeyboard());
        onView(withId(R.id.btnAfficher)).perform(click());

        onView(withId(R.id.lblMessage)).check(matches(withText(R.string.erreur9)));
    }
}

