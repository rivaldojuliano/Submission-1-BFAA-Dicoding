package dev.codewithrivaldo.mysubmission.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize //add annotation parcelize
data class User(
    val name: String,
    val username: String,
    val avatar: Int,
    val company: String,
    val location: String,
    val repository: String,
    val follower: String,
    val following: String
): Parcelable //add parcelable