package com.mukeshsolanki.countrypickerexample.auth

import com.mukeshsolanki.countrypickerexample.Account
import com.mukeshsolanki.countrypickerexample.Session
import com.mukeshsolanki.countrypickerexample.di.SessionComponentManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    private val sessionComponentManager: SessionComponentManager
) {

    /**
     * Logs the user in: persists the account and creates the SessionComponent.
     */
    suspend fun login(username: String, password: String): Result<Account> {
        return try {
            val account = performLogin(username, password)
            Session.storeAccount(account)
            sessionComponentManager.getOrCreateSessionComponent()
            Result.success(account)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Logs the user out: clears the stored account and destroys the SessionComponent.
     */
    fun logout() {
        Session.clearAccount()
        sessionComponentManager.clearSessionComponent()
    }

    private suspend fun performLogin(username: String, password: String): Account {
        return withContext(Dispatchers.IO) {
            Account(
                userId = "user_${System.currentTimeMillis()}",
                username = username,
                token = "token_${System.currentTimeMillis()}"
            )
        }
    }
}
