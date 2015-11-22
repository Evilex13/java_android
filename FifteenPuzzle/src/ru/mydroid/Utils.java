package ru.mydroid;

/**
 * Created by Evilex on 12.11.2015.
 */

import android.app.Activity;
        import android.content.Intent;
public class Utils
{
    private static int sTheme;
    /**
     * Set the theme of the Activity, and restart it by creating a new Activity of the same type.
     */
    public static void changeToTheme(Activity activity, int theme)
    {
        sTheme = theme;
        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }
    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case R.style.Theme13:
                activity.setTheme(R.style.Theme13);
                break;
            case R.style.Theme14:
                activity.setTheme(R.style.Theme14);
                break;
            case R.style.Theme15:
                activity.setTheme(R.style.Theme15);
                break;
        }
    }
}



