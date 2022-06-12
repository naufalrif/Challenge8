package com.example.challenge8.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserManager(context : Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore("user_login")

    companion object{
        val ID = preferencesKey<String>("user_id")
        val USERNAME = preferencesKey<String>("user_username")
        val PASSWORD = preferencesKey<String>("user_password")
        val EMAIL= preferencesKey<String>("user_email")
        val BOOLEAN = preferencesKey<Boolean>("boolean")


    }

    suspend fun setBoolean(boolean: Boolean){
        dataStore.edit {
            it[BOOLEAN] = boolean
        }
    }
    suspend fun saveData(id:String, username:String, password:String, email : String){
        dataStore.edit {
            it[ID] = id
            it[USERNAME] = username
            it[PASSWORD] = password
            it[EMAIL] = email

        }
    }

    suspend fun clearData(){
        dataStore.edit {
            it.clear()
        }
    }

    val userUsername : Flow<String> = dataStore.data.map {
        it[USERNAME] ?: ""
    }

    val userPassword : Flow<String> = dataStore.data.map {
        it[PASSWORD] ?: ""
    }

    val userEmail : Flow<String> = dataStore.data.map {
        it[EMAIL] ?: ""
    }

    val boolean : Flow<Boolean> = dataStore.data.map {
        it[BOOLEAN]?: false
    }


}