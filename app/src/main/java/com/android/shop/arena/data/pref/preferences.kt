package com.android.shop.arena.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {


    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MyDataStore")
        val UID_KEY = stringPreferencesKey("uid_key")
    }

    val uidFlow: Flow<String?> =
        context.dataStore.data.map { preferences ->
            preferences[UID_KEY]
        }

    suspend fun saveUID(uid: String) {
        context.dataStore.edit { preferences ->
            preferences[UID_KEY] = uid
        }
    }

    suspend fun deleteUID() {
        context.dataStore.edit { preferences ->
            preferences.remove(UID_KEY)
        }
    }

    suspend fun isUIDEmpty(): Boolean {
        val uid = uidFlow.first()
        return uid.isNullOrEmpty()
    }

}

