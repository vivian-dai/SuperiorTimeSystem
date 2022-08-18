package com.example.superiortimesystem

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.example.superiortimesystem.datetime.DateTime

/**
 * Widget to display the date in IFC
 * Child of `AppWidgetProvider`
 */
class InternationalFixedCalendarDate : AppWidgetProvider() {
    /**
     * Updates all of the widgets
     *
     * @param context the state of the widget
     * @param appWidgetManager manages the app's widgets (states, updating, etc.)
     * @param appWidgetIds array of the IDs of all of the widgets on screen
     */
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateIFCAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created

    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    /**
     * What to do upon receiving a broadcast message
     *
     * The widget is to be updated every time the minute changes
     * which is received on the `ACTION_TIME_TICK` `Intent`
     * after failing to get `ACTION_DATE_CHANGED` to work
     *
     * @param context the state
     * @param intent the specific broadcast message sent
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val action: String? = intent?.action
        if((action != null) && (action == Intent.ACTION_TIME_TICK) && (context != null)) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val ids = appWidgetManager.getAppWidgetIds(ComponentName(context, InternationalFixedCalendarDate::class.java))
            for (id in ids) {
                updateIFCAppWidget(context, appWidgetManager, id)
            }
        }
    }

}
/**
 * Updates an individual app widget by pulling the
 * current IFC date and updating the view
 *
 * @param context the state of the widget
 * @param appWidgetManager the component responsible for updating the widget
 * @param appWidgetId the ID of the specific widget to update
 */
internal fun updateIFCAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.international_fixed_calendar_date)
    views.setTextViewText(R.id.appwidget_text_ifc, DateTime().ifcDate())

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}