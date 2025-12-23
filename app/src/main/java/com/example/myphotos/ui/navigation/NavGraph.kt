package com.example.myphotos.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myphotos.ui.view.AddImageScreenRoute
import com.example.myphotos.ui.view.HomeScreenRoute
import com.example.myphotos.ui.view.ImageScreenRoute
import com.example.myphotos.ui.viewmodel.ImagesViewModel

@Composable
fun NavGraph(viewModel: ImagesViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Routes.ImageHome
    ) {
        composable<Routes.ImageHome>(
            enterTransition = {
                fadeIn() + slideInHorizontally { -it }
            },
            exitTransition = {
                fadeOut() + slideOutHorizontally { it }
            }
        ) {
            HomeScreenRoute(
                viewModel,
                onNavImage = { id -> navController.navigate(Routes.ImageView(id)) },
                navAddImageView = { navController.navigate(Routes.AddImage) }
            )
        }
        composable<Routes.ImageView>(
            enterTransition = {
                slideInHorizontally { it } + fadeIn()
            },
            exitTransition = {
                slideOutHorizontally { -it } + fadeOut()
            },
            popEnterTransition = {
                slideInHorizontally { -it } + fadeIn()
            },
            popExitTransition = {
                slideOutHorizontally { it } + fadeOut()
            }
        ) { backStackEntry ->

            val args = backStackEntry.toRoute<Routes.ImageView>()

            ImageScreenRoute(
                viewModel = viewModel,
                id = args.id,
                onNavBack = { navController.popBackStack() }
            )
        }
        composable<Routes.AddImage> {
            AddImageScreenRoute(viewModel) { navController.popBackStack() }
        }
    }

}