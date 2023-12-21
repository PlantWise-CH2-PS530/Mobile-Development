package com.dicoding.plantwiseapp.ui.profile

import androidx.lifecycle.LiveData
import com.dicoding.plantwiseapp.database.profiledb.Profile
import com.dicoding.plantwiseapp.database.profiledb.ProfileDao

class ProfileRepository(private val profileDao: ProfileDao) {

    suspend fun getProfileSuspend(): Profile? {
        return profileDao.getProfile()
    }

    fun getProfile(): LiveData<Profile?> {
        return profileDao.getProfileLiveData()
    }

    suspend fun insertProfile(profile: Profile) {
        profileDao.insert(profile)
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

    companion object {
        @Volatile
        private var instance: ProfileRepository? = null
        fun getInstance(
            profileDao: ProfileDao
        ): ProfileRepository =
            instance ?: synchronized(this) {
                instance ?: ProfileRepository(profileDao)
            }.also { instance = it }
    }
}