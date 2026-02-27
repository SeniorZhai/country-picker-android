package com.mukeshsolanki.countrypickerexample.di

import android.content.Context
import com.mukeshsolanki.countrypickerexample.Account
import com.mukeshsolanki.countrypickerexample.Session
import com.mukeshsolanki.countrypickerexample.SessionScope
import com.mukeshsolanki.countrypickerexample.db.UserDao
import com.mukeshsolanki.countrypickerexample.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(SessionComponent::class)
object SessionModule {

    /**
     * Provides the currently logged-in account.
     * Requires that Session.storeAccount() has already been called.
     */
    @Provides
    @SessionScope
    fun provideAccount(): Account {
        return Session.getAccount()
            ?: throw IllegalStateException("No account available")
    }

    /**
     * Provides the UserRepository for the active session.
     * Database/DAO bindings are handled by BaseDbModule.
     */
    @Provides
    @SessionScope
    fun provideUserRepository(
        userDao: UserDao,
        account: Account
    ): UserRepository {
        return UserRepository(userDao, account)
    }
}

