package com.example.android.mygarden;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.example.android.mygarden.utils.PlantUtils;
import com.example.android.mygarden.provider.PlantContract;


import com.example.android.mygarden.ui.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class PlantWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int imgRes,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.plant_widget_provider);


        // Create an intent to launch MainActivity when clicked
        Intent intent = new Intent (context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Widget allow click handlers to only launch pending intents
        views.setOnClickPendingIntent(R.id.plant_detail_image, pendingIntent);

        //update image
        views.setImageViewResource(R.id.widget_plant_image, imgRes);
        views.setOnClickPendingIntent(R.id.widget_plant_image, pendingIntent);


        // Add the wateringservice click handler
        Intent wateringIntent = new Intent(context, PlantWateringService.class);
        wateringIntent.setAction(PlantWateringService.ACTION_WATER_PLANTS);
        PendingIntent wateringPendingIntent = PendingIntent.getService(context, 0, wateringIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_water_button, wateringPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // This is called everytime a new widget is created and after the interval ( 30 min ) happens


        // There may be multiple widgets active, so update all of them
        PlantWateringService.setActionUpdatePlantWidgets(context);
    }


    public static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager, int imgRes, int[] appWidgetIds){

        for (int appWidgetId : appWidgetIds){

            updateAppWidget(context, appWidgetManager, imgRes, appWidgetId);

        }

    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

