package com.dicoding.plantwiseapp.database.profiledb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profile: Profile)

    @Query("SELECT * FROM profile LIMIT 1")
    suspend fun getProfile(): Profile?

    @Query("SELECT * FROM profile LIMIT 1")
    fun getProfileLiveData(): LiveData<Profile?>

    @Query("SELECT * from profile")
    fun getAllFavorite(): LiveData<List<Profile>>

    @Query("DELETE FROM profile WHERE username = :username")
    suspend fun deleteProfileByUsername(username: String)

    @Query("SELECT * FROM profile WHERE username = :username")
    fun getProfileByUsername(username: String): LiveData<Profile>
}