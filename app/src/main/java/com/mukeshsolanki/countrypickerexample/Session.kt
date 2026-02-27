package com.mukeshsolanki.countrypickerexample

import android.content.Context
import android.content.SharedPreferences
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

object Session {
    private const val PREF_NAME = "session_prefs"
    private const val KEY_ACCOUNT = "account"

    private var sharedPreferences: SharedPreferences? = null

    /**
     * Must be called in Application.onCreate()
     */
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Returns the currently logged-in account, or null if not logged in.
     */
    fun getAccount(): Account? {
        val json = sharedPreferences?.getString(KEY_ACCOUNT, null)
        return json?.let {
            try {
                Json.decodeFromString<Account>(it)
            } catch (e: Exception) {
                null
            }
        }
    }

    /**
     * Persists the account after a successful login.
     */
    fun saveAccount(account: Account) {
        val json = Json.encodeToString(account)
        sharedPreferences?.edit()?.putString(KEY_ACCOUNT, json)?.apply()
    }

    /**
     * Removes the stored account on logout.
     */
    fun clearAccount() {
        sharedPreferences?.edit()?.remove(KEY_ACCOUNT)?.apply()
    }

    /**
     * Returns true if an account is currently stored.
     */
    fun isLoggedIn(): Boolean = getAccount() != null
}
