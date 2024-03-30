package com.mhmtn.moviearea2

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mhmtn.moviearea2.ui.theme.MovieArea2Theme
import com.mhmtn.moviearea2.viewmodel.MovieListViewModel
import com.mhmtn.moviearea2.views.HomeScreen
import com.mhmtn.moviearea2.views.MovieDetailScreen
import com.mhmtn.moviearea2.views.MovieListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieArea2Theme {

                WindowCompat.setDecorFitsSystemWindows(window,false)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
                )

                val linearGradientBrush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFB226E1),
                        Color(0xFFFC6603),
                        Color(0xFF5995EE),
                        Color(0xFF3D3535)
                    ),
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY)
                )

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(linearGradientBrush)
                    ){
                        NavHost(navController = navController, startDestination = "home_screen"){
                            composable("home_screen") {
                                HomeScreen(navController = navController)
                            }

                            composable("movie_list_screen"){
                                MovieListScreen(navController = navController)
                            }

                            composable("movie_detail_screen/{id}", arguments = listOf(
                                navArgument(
                                    name = "id"
                                ){
                                    type = NavType.IntType
                                }
                            )){
                                it.arguments?.getInt("id")?.let {id->
                                    MovieDetailScreen(id = id )
                                }

                            }

                        }
                    }

                }
            }
        }
    }
}
