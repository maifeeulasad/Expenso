package dev.spikeysanju.expensetracker.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton


class AuthDataStore(context: Context) :
    PrefsDataStore(
        context,
        PREF_FILE_AUTH
    ),
    AuthImpl {

    // used to get the data from datastore
    override val authenticated: Flow<Boolean>
        get() = dataStore.data.map { preferences ->
            val authenticated = preferences[AUTHENTICATED_KEY] ?: false
            authenticated
        }

    // used to save the authenticated status to datastore
    override suspend fun saveToDataStore(isAuthenticated: Boolean) {
        dataStore.edit { preferences ->
            preferences[AUTHENTICATED_KEY] = isAuthenticated
        }
    }

    companion object {
        private const val PREF_FILE_AUTH = "auth_preference"
        private val AUTHENTICATED_KEY = booleanPreferencesKey("authenticated")
    }
}

@Singleton
interface AuthImpl {
    val authenticated: Flow<Boolean>
    suspend fun saveToDataStore(isAuthenticated: Boolean)
}
