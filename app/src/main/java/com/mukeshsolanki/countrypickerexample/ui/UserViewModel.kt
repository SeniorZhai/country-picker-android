package com.mukeshsolanki.countrypickerexample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mukeshsolanki.countrypickerexample.db.User
import com.mukeshsolanki.countrypickerexample.di.SessionComponentManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val sessionComponentManager: SessionComponentManager
) : ViewModel() {

    private val _userState = MutableStateFlow<UserState>(UserState.Loading)
    val userState: StateFlow<UserState> = _userState

    /**
     * Loads user data from the session-scoped repository.
     */
    fun loadUserData() {
        viewModelScope.launch {
            try {
                val repository = sessionComponentManager
                    .getOrCreateSessionComponent()
                    .getUserRepository()
                val data = repository.getUserData()
                _userState.value = UserState.Success(data)
            } catch (e: IllegalStateException) {
                _userState.value = UserState.NotLoggedIn
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "Failed to load user data")
            }
        }
    }

    /**
     * Saves a user record via the session-scoped repository.
     */
    fun saveUser(user: User) {
        viewModelScope.launch {
            try {
                val repository = sessionComponentManager
                    .getOrCreateSessionComponent()
                    .getUserRepository()
                repository.saveUser(user)
                loadUserData()
            } catch (e: IllegalStateException) {
                _userState.value = UserState.NotLoggedIn
            }
        }
    }
}
