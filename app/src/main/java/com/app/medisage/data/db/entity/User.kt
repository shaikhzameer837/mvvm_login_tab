package com.app.medisage.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val email: String?,
    val password: String?) : Parcelable