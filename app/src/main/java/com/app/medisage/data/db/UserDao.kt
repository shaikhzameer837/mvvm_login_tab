package com.app.medisage.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.medisage.data.db.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): LiveData<List<User>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    @Query("DELETE FROM user")
    suspend fun clearUser()

    @Query("DELETE FROM user WHERE id = :id") //you can use this too, for delete User by id.
    suspend fun deleteUserById(id: Int)
}