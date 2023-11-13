package com.wiprotest.navigations

sealed class Screen(val route: String) {
    object UniversitiesScreen: Screen("university_screen")
}