package com.app.medisage.data.repository

import androidx.lifecycle.LiveData
import com.app.medisage.data.db.AppDataBase
import com.app.medisage.data.db.entity.User

class UserRepository(
    private val appDataBase: AppDataBase
) {

    suspend fun insertUser(user: User) = appDataBase.getUserDao().insertUser(user)

    suspend fun updateUser(user: User) = appDataBase.getUserDao().updateUser(user)

    suspend fun deleteUser(user: User) = appDataBase.getUserDao().deleteUser(user)

    suspend fun deleteUserById(id: Int) = appDataBase.getUserDao().deleteUserById(id)

    suspend fun clearUser() = appDataBase.getUserDao().clearUser()

    fun getAllUsers(): LiveData<List<User>> = appDataBase.getUserDao().getAllUsers()
}