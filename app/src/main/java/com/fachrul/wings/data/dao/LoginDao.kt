package com.fachrul.wings.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fachrul.wings.data.entity.LoginEntity

@Dao
interface LoginDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun registerUser(loginEntity: LoginEntity)

    @Query("SELECT * from login  WHERE user = :user")
    fun getUser(user:String): LiveData<LoginEntity>
}