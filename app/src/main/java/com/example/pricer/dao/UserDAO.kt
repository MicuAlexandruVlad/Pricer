package com.example.pricer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pricer.models.User

@Dao
interface UserDAO {

    @Insert
    fun insertUser(user: User)

    @Query("Select * From Users")
    fun getRegisteredUser(): User
}