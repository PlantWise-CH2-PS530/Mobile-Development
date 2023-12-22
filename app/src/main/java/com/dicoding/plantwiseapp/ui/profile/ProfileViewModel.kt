package com.dicoding.plantwiseapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.plantwiseapp.database.profiledb.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

//    private val viewModelScope = CoroutineScope(Dispatchers.Main)
    init {
        viewModelScope.launch {
            profileRepository.initializeProfileLiveData()
        }
    }

    fun getProfile(): LiveData<Profile?> {
        return profileRepository.getProfile()
    }

    fun saveProfileImage(imageUri: String) {
        viewModelScope.launch {
            val profile = profileRepository.getProfileSuspend() ?: Profile(username = "", avatarUrl = "")
            profile.avatarUrl = imageUri
            profileRepository.insertProfile(profile)
        }
    }

    fun saveUsername(username: String) {
        viewModelScope.launch {
            val profile = profileRepository.getProfileSuspend() ?: Profile(username = "", avatarUrl = "")
            profile.username = username
            profileRepository.insertProfile(profile)
        }
    }
    fun getUsernameFromPrefs(): String {
        return profileRepository.getUsernameFromPrefs()
    }
}