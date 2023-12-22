package com.dicoding.plantwiseapp.data.di

import android.content.Context
import com.dicoding.plantwiseapp.database.profiledb.ProfileRoomDatabase
import com.dicoding.plantwiseapp.ui.profile.ProfileRepository

object Injection {
    fun provideRepository(context: Context): ProfileRepository {
        val database = ProfileRoomDatabase.getDatabase(context)
        val dao = database.profileDao()
        val cont = context.applicationContext
        return ProfileRepository.getInstance(dao, cont)
    }
}