package com.example.luispena.twoactivities;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class SpinnerSelectionTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {

        //Context appContext = InstrumentationRegistry.getTargetContext();
        //assertEquals("com.exmpaple.luispena")

    }

}
