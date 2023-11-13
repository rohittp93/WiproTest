package com.wiprotest.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wiprotest.presentation.universities_screen.UniversitiesScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

object Graph {
    const val ROOT = "root_graph"
}

@OptIn(InternalCoroutinesApi::class, ExperimentalCoroutinesApi::class)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Screen.UniversitiesScreen.route,
    ) {
         composable("university_screen") {
             UniversitiesScreen(navController = navController)
        }
    }
}
