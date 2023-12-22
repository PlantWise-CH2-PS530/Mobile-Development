package com.dicoding.plantwiseapp.ui.profile

import android.annotation.SuppressLint
import android.content.Context
import androidx.datastore.preferences.core.preferencesOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.dicoding.plantwiseapp.database.profiledb.Profile
import com.dicoding.plantwiseapp.database.profiledb.ProfileDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val profileDao: ProfileDao, private val context: Context) {

    private val profileLiveData: MutableLiveData<Profile?> = MutableLiveData()

    init {
//        viewModelScope.launch {
//            initializeProfileLiveData()
//        }
    }

    internal suspend fun initializeProfileLiveData(){
        withContext(Dispatchers.IO){
            val profile =
                profileDao.getProfile()
            profileLiveData.postValue(profile)
        }
    }
    suspend fun getProfileSuspend(): Profile? {
        return profileDao.getProfile()
    }

    fun getProfile(): LiveData<Profile?> {
        return profileLiveData
    }

    suspend fun insertProfile(profile: Profile) {
        profileDao.insert(profile)
        profileLiveData.postValue(profile)
    }

    suspend fun saveProfileImage(imageUri: String) {
        val existingProfile = profileDao.getProfile()

        existingProfile?.let {
            val updatedProfile = it.copy(avatarUrl = imageUri)
            insertProfile(updatedProfile)
        }
    }

    suspend fun saveUsername(newUsername: String) {
        val existingProfile = profileDao.getProfile()

        existingProfile?.let {
            val updatedProfile = it.copy(username = newUsername)
            insertProfile(updatedProfile)
        }
    }

    fun saveUsernameToPrefs(newUsername: String) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("username", newUsername).apply()
    }

    // Retrieve username from SharedPreferences
    fun getUsernameFromPrefs(): String {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: ProfileRepository? = null
        fun getInstance(
            profileDao: ProfileDao,
            context: Context
        ): ProfileRepository =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(profileDao, context)
            }.also { instance = it }
    }
}