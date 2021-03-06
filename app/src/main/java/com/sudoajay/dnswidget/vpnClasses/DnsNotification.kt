package com.sudoajay.dnswidget.vpnClasses


import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.sudoajay.dnswidget.R
import com.sudoajay.dnswidget.ui.customDns.database.Dns


class DnsNotification(private val context: Context) {
    var notificationManager: NotificationManager? = null
    var notification: Notification? = null

    fun notifyBuilder(title: String, builder: Notification.Builder, dns: Dns) { // local variable


        // now check for null notification manger
        if (notificationManager == null) {
            notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        builder

            .setContentTitle(title)
            .setContentText((if (dns.filter == "None") dns.dnsName else dns.dnsName + " (" + dns.filter + ")") + ". Expand to see more ")
            .setVisibility(Notification.VISIBILITY_PUBLIC)
            .setOngoing(true)
            .setAutoCancel(true)
        


        val iStyle =
            Notification.InboxStyle()
        iStyle.addLine(if (dns.filter == "None") dns.dnsName else dns.dnsName + " (" + dns.filter + ")")
        iStyle.addLine("\n")
        iStyle.addLine(dns.dns1)
        iStyle.addLine(dns.dns2)
        iStyle.addLine(dns.dns3)
        iStyle.addLine(dns.dns4)
        builder.style = iStyle

        // check if there ia data with empty
// more and view button classification
         notification = builder.build()

        notification!!.flags =
            notification!!.flags or (Notification.FLAG_NO_CLEAR or Notification.FLAG_ONGOING_EVENT)

        notifyNotification(notification!!)
    }

    fun notifyCompat(
        title: String,
        builder: NotificationCompat.Builder,
        dns: Dns
    ) { // local variable

//        Pending Intent For Pause Action
        val pausePendingIntent = PendingIntent.getService(
            context, AdVpnService.REQUEST_CODE_PAUSE, Intent(context, AdVpnService::class.java)
                .putExtra("COMMAND", Command.PAUSE.ordinal), 0
        )

//        Pending Intent For Stop Action
        val stopPendingIntent = PendingIntent.getService(
            context, AdVpnService.REQUEST_CODE_STOP, Intent(context, AdVpnService::class.java)
                .putExtra("COMMAND", Command.STOP.ordinal), 0
        )

        // now check for null notification manger
        if (notificationManager == null) {
            notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        // Default ringtone
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        builder

            .addAction(
                R.drawable.ic_pause,
                context.getString(R.string.notification_action_pause),
                pausePendingIntent
            )

            .addAction(
                R.drawable.ic_stop, context.getString(R.string.stop_text),
                stopPendingIntent
            )

            // Set appropriate defaults for the notification light, sound,
            // and vibration.
            .setDefaults(Notification.DEFAULT_ALL) // Set required fields, including the small icon, the
            .setContentTitle(title)
            .setContentText((if (dns.filter == "None") dns.dnsName else dns.dnsName + " (" + dns.filter + ")") + ". Expand to see more ")

            .setOngoing(true)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setSound(uri) // Provide a large icon, shown with the notification in the

            .color = ContextCompat.getColor(context, R.color.primaryAppColor)
        // If this notification relates to a past or upcoming event, you

        //Content hen expanded


        val iStyle =
            NotificationCompat.InboxStyle()
        iStyle.addLine(if (dns.filter == "None") dns.dnsName else dns.dnsName + " (" + dns.filter + ")")
        iStyle.addLine("\n")
        iStyle.addLine(dns.dns1)
        iStyle.addLine(dns.dns2)
        iStyle.addLine(dns.dns3)
        iStyle.addLine(dns.dns4)
        builder.setStyle(iStyle)

        // check if there ia data with empty
// more and view button classification
        notification = builder.build()

        notification!!.flags =
            notification!!.flags or (Notification.FLAG_NO_CLEAR or Notification.FLAG_ONGOING_EVENT)

        notifyNotification(notification!!)
    }

     fun notifyNotification(notification: Notification) {
        notificationManager!!.notify(AdVpnService.NOTIFICATION_ID_STATE, notification)

    }



}