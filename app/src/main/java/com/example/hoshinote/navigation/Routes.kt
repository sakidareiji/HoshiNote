package com.example.hoshinote.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object UserRegistration : Routes()

    @Serializable
    data object GoalSetting : Routes()

    @Serializable
    data object GoalManagement : Routes()

    @Serializable
    data object Setting : Routes()
}