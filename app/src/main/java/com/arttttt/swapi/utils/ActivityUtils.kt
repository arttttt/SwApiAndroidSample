package com.arttttt.swapi.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

object ActivityUtils {
    inline fun <reified T: AppCompatActivity>startActivity(context: Context,
                                                           activityOptions: Bundle?,
                                                           intentExtra: Bundle?,
                                                           flags: Int) {
        val intent = Intent(context, T::class.java).apply {
            this.flags = this.flags or flags
            if (intentExtra != null)
                this.putExtras(intentExtra)
        }

        if (activityOptions != null)
            context.startActivity(intent, activityOptions)
        else
            context.startActivity(intent)
    }
}