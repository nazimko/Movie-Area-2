package com.mhmtn.moviearea2.views

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mhmtn.moviearea2.models.Data
import com.mhmtn.moviearea2.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: NavHostController) {

    val movieViewModel = viewModel<MovieViewModel>()
    val state = movieViewModel.state

    Scaffold (
        modifier = Modifier.background(Color.Transparent),
        containerColor = Color.Transparent
    ) {
        Column {
            SearchBar(
                modifier = Modifier.padding(top = 40.dp)
            ){
                movieViewModel.getMovieListBySearch(it)
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(
                        Color.Transparent
                    )
            ){
                items(state.movies.size){
                    MovieCard(index = it, movieList = state.movies, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MovieCard(index : Int, movieList : List<Data>, navController: NavHostController) {
    Card(
        Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable {
                navController.navigate("movie_detail_screen/${movieList[index].id}")
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            AsyncImage(
                model = movieList[index].poster,
                contentDescription = movieList[index].title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.LightGray.copy(0.7f))
                    .padding(6.dp)
            ) {
                Text(
                    text = movieList[index].title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .basicMarquee(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    style = TextStyle(
                        shadow = Shadow(
                            color = Color(0xFFFC6603),
                            offset = Offset(1f,1f), blurRadius = 3f
                        )
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    modifier = Modifier.align(Alignment.End)
                ){
                    Icon(imageVector = Icons.Rounded.Star, contentDescription = "Rating")
                    Text(
                        text  = movieList[index].imdb_rating,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                        )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Movie Area 2", textAlign = TextAlign.Center)},
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White.copy(0.4f))
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(modifier: Modifier = Modifier,
              onSearch : (String) -> Unit = {}
) {
    var text = remember { mutableStateOf("") }

    Box (modifier = modifier) {
        TextField(value = text.value, onValueChange = {
            text.value=it
            onSearch(it)
        },  textStyle = TextStyle(color = MaterialTheme.colorScheme.tertiary),
            shape = RoundedCornerShape(12.dp),
            label = { Text(text = "Search Crypto") },
            placeholder = { Text(text = "Search..") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp)
                .background(color = Color.White, CircleShape),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Visibility Icon"
                )
            }
        )
    }
}