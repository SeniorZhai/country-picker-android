package com.mukeshsolanki.countrypickerexample.di

import android.content.Context
import androidx.room.Room
import com.mukeshsolanki.countrypickerexample.Account
import com.mukeshsolanki.countrypickerexample.Session
import com.mukeshsolanki.countrypickerexample.SessionScope
import com.mukeshsolanki.countrypickerexample.db.UserDao
import com.mukeshsolanki.countrypickerexample.db.UserDatabase
import com.mukeshsolanki.countrypickerexample.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(SessionComponent::class)
object SessionModule {

    /**
     * Provides the currently logged-in account.
     */
    @Provides
    @SessionScope
    fun provideAccount(): Account {
        return Session.getAccount()
            ?: throw IllegalStateException("No account available")
    }

    /**
     * Provides a per-user Room database (isolated by userId).
     */
    @Provides
    @SessionScope
    fun provideUserDatabase(
        @ApplicationContext context: Context,
        account: Account
    ): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "user_${account.userId}_database"
        ).build()
    }

    /**
     * Provides the UserDao from the session-scoped database.
     */
    @Provides
    @SessionScope
    fun provideUserDao(database: UserDatabase): UserDao {
        return database.userDao()
    }

    /**
     * Provides the UserRepository for the active session.
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
