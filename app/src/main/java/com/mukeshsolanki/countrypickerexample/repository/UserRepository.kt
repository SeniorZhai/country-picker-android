package com.mukeshsolanki.countrypickerexample.repository

import com.mukeshsolanki.countrypickerexample.Account
import com.mukeshsolanki.countrypickerexample.db.User
import com.mukeshsolanki.countrypickerexample.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val account: Account
) {
    suspend fun getUserData(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun saveUser(user: User) {
        userDao.insert(user)
    }
}
