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
    val architect: String,       // Architect of the structure
    val location: String,        // Location (City, Country)
    val yearCompleted: Int,      // Year the structure was completed
    val style: String,           // Architectural style
    val height: Int,             // Height of the structure in meters
    val description: String,
    val imageResource: Int

) : Parcelable {

}

