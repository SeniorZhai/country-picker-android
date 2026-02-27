package com.mukeshsolanki.countrypickerexample.ui

import com.mukeshsolanki.countrypickerexample.db.User

sealed class UserState {
    object Loading : UserState()
    object NotLoggedIn : UserState()
    data class Success(val users: List<User>) : UserState()
    data class Error(val message: String) : UserState()
}
