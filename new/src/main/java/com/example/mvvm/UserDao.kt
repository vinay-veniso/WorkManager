package com.example.mvvm

import androidx.room.*
import com.example.mvvm.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: MutableList<User>)

    @Insert
    suspend fun insertUser(user:User)

    @Delete
    suspend fun delete(user: User)

}