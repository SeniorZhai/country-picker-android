package com.mukeshsolanki.countrypickerexample.di

import com.mukeshsolanki.countrypickerexample.SessionScope
import com.mukeshsolanki.countrypickerexample.db.UserDao
import com.mukeshsolanki.countrypickerexample.db.UserDatabase
import com.mukeshsolanki.countrypickerexample.repository.UserRepository
import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@SessionScope
@DefineComponent(parent = SingletonComponent::class)
interface SessionComponent {

    fun getUserRepository(): UserRepository
    fun getUserDao(): UserDao
    fun getUserDatabase(): UserDatabase

    @DefineComponent.Builder
    interface Builder {
        fun build(): SessionComponent
    }
}
