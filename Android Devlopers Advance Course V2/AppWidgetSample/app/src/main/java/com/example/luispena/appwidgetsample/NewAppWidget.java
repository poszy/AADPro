package com.example.luispena.appwidgetsample;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private static final String mSharedPref = "com.example.luispena.appwidgetsample";
    private static final String COUNT_KEY = "count";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);
        SharedPreferences prefs = context.getSharedPreferences(mSharedPref, 0);

        // Get the count number from prefs
        int count = prefs.getInt(COUNT_KEY + appWidgetId, 0);
        count++;

        // get the current time and format it
        String dateString = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        views.setTextViewText(R.id.appwidget_id, String.valueOf(appWidgetId));
        views.setTextViewText(R.id.appwidget_update, context.getResources().getString(R.string.date_count_format, count, dateString));

        // Save the current count back into prefs
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putInt(COUNT_KEY + appWidgetId, count);
        prefEditor.apply();

        // setup the update button to send an update request as a pending itnent
        Intent i = new Intent(context, NewAppWidget.class);

        // the intnet action must be an app widget update
        i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // Include the widget ID to be updated as an intent extra
        int[] idArray = new int[]{appWidgetId};
        i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        // Wrap it all in a pending intent to send a broadcast.
        // Use the app widget ID as the request code (third argument) so that
        // each intent is unique.
        PendingIntent pi = PendingIntent.getBroadcast(context, appWidgetId, i, PendingIntent.FLAG_UPDATE_CURRENT);

        // Assign the pending intent to the button OnClick Handler
        views.setOnClickPendingIntent(R.id.button_update, pi);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

}

