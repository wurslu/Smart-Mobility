package com.example.smartmobility.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Profile(
    val username: String,
    val userId: String,
    val collections: UInt,
    val foots: UInt,
    val coupons: UInt,
    val integral: UInt
)

class ProfileScreen : ViewModel() {
   private val _profile = MutableStateFlow<Profile>(
        Profile(
            username = "张雨晨",
            userId = "123456",
            collections = 87.toUInt(),
            foots = 98.toUInt(),
            coupons = 5.toUInt(),
            integral = 1000.toUInt(),
        )
    )
    val profile = _profile.asStateFlow()
}