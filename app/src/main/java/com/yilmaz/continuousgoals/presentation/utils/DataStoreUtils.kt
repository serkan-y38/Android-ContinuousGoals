package com.yilmaz.continuousgoals.presentation.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.yilmaz.continuousgoals.common.Constants
import com.yilmaz.continuousgoals.common.Constants.DATA_STORE_PREFERENCES_NAME
import com.yilmaz.continuousgoals.common.Constants.THEME_PREFERENCES_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreUtils(private val context: Context) {

    fun getTheme(): Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: Constants.themes[0]
        }

    suspend fun setTheme(theme: String) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            DATA_STORE_PREFERENCES_NAME
        )
        val THEME_KEY = stringPreferencesKey(
            THEME_PREFERENCES_KEY
        )
    }

}