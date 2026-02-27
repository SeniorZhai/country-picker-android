package com.mukeshsolanki.countrypickerexample.di

import com.mukeshsolanki.countrypickerexample.Session
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SessionComponentModule {

    @Provides
    @Singleton
    fun provideSessionComponentManager(
        sessionComponentBuilder: SessionComponent.Builder
    ): SessionComponentManager {
        return SessionComponentManager(sessionComponentBuilder)
    }
}

@Singleton
class SessionComponentManager(
    private val sessionComponentBuilder: SessionComponent.Builder
) {
    private var sessionComponent: SessionComponent? = null

    /**
     * Returns an existing SessionComponent, or creates one if the user is logged in.
     * @throws IllegalStateException if the user is not logged in.
     */
    fun getOrCreateSessionComponent(): SessionComponent {
        if (sessionComponent == null && Session.isLoggedIn()) {
            sessionComponent = sessionComponentBuilder.build()
        }
        return sessionComponent ?: throw IllegalStateException("User not logged in")
    }

    /**
     * Destroys the SessionComponent on logout.
     */
    fun clearSessionComponent() {
        sessionComponent = null
    }

    /**
     * Returns true if a SessionComponent is currently active.
     */
    fun hasActiveSession(): Boolean = sessionComponent != null
}
