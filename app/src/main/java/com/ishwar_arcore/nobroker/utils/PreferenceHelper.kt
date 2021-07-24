package com.ishwar_arcore.nobroker.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {
    companion object {

        private var sharedPreferences: SharedPreferences? = null

        fun getSharedPreferences(context: Context): SharedPreferences? {
            if (sharedPreferences == null) {
                sharedPreferences =
                    context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            }
            return sharedPreferences
        }

        fun writeBooleanToPreference(key: String?, value: Boolean) {
            val editor = sharedPreferences!!.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun getBooleanFromPreference(key: String?): Boolean {
            return sharedPreferences!!.getBoolean(key, true)
        }

    }
}