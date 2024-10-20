package com.example.nit3213project
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)
@Parcelize
data class Entity(
    val name: String,
    val culture: String,
    val domain: String,
    val symbol: String,
    val parentage: String,
    val romanEquivalent: String,
    val description: String
) :Parcelable
