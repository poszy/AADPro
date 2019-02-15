package com.example.android.teatime;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.android.teatime.model.Tea;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

@RunWith(AndroidJUnit4.class)
public class MenuActivityScreenTest {


    public static final String TEA_NAME = "Green Tea";
    //Define rule: this will tell the compiler to use the MenuActivity for the test
    @Rule
    public ActivityTestRule<MenuActivity> menuActivityActivityTestRule = new ActivityTestRule<>(MenuActivity.class);

    // Rule to test the GridView
    @Test
    public void clickGridViewItem_OpensOrderActivity(){

        // TODO (3) Finish writing this test which will click on a gridView Tea item and verify that
        // the OrderActivity opens up with the correct tea name displayed.

        // 1. Click on a gridview
        onData(anything())
                .inAdapterView(withId(R.id.tea_grid_view)).atPosition(1).perform(click());

        // 2. verify the orderactivity opens
        onView(withId(R.id.tea_name_text_view)).check(matches(withText(TEA_NAME)));
        // 3. verify

    }


}

