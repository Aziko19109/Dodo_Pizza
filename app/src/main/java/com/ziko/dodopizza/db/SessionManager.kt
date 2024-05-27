package com.ziko.dodopizza.db

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var isRegistered: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_REGISTERED, false)
        set(isRegistered) {
            sharedPreferences.edit().putBoolean(KEY_IS_REGISTERED, isRegistered).apply()
        }
    var name: String?
        get() = sharedPreferences.getString(KEY_NAME, "")
        set(restaurantName) {
            sharedPreferences.edit().putString(KEY_NAME, restaurantName).apply()
        }
    var phone: String?
        get() = sharedPreferences.getString(KEY_PHONE, "")
        set(status) {
            sharedPreferences.edit().putString(KEY_PHONE, status).apply()
        }


    companion object {
        private const val PREFS_NAME = "MyPrefs"
        private const val KEY_IS_REGISTERED = "isRegistered"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE = "phone"


    }



}
