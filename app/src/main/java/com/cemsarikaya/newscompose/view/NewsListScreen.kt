package com.cemsarikaya.newscompose.view
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.cemsarikaya.newscompose.model.Article
import com.cemsarikaya.newscompose.viewmodel.NewsListViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun NewsListScreen(navController: NavController, viewModel: NewsListViewModel = hiltViewModel()){

      Surface(color = MaterialTheme.colors.surface,modifier = Modifier.fillMaxSize()){


            Column(modifier=Modifier.background(color = MaterialTheme.colors.surface)){

                  Column(modifier=Modifier.background(color = MaterialTheme.colors.primary)) {


                        Text(text = "HABERLER", modifier = Modifier
                              .fillMaxWidth()
                              .clip(RoundedCornerShape(size = 10.dp)),
                              textAlign = TextAlign.Center,
                              fontSize = 40.sp,
                              fontWeight = FontWeight.Bold,
                              color =MaterialTheme.colors.background

                              )


                  }
            Spacer(modifier = Modifier.height(5.dp))

                  //haberler
                 NewsLists(navController = navController)
            }

}

}


@Composable
fun NewsLisView(newsies:List<Article>,navController: NavController) {

      val viewModel: NewsListViewModel = viewModel()
      val isRefreshing by viewModel.isRefreshing.collectAsState()

      SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refresh() },
      ) {

            LazyColumn(contentPadding = PaddingValues(5.dp)) {



                  items(newsies) { news ->


                        newsRow(navController = navController, news = news)



            }

            }
      }
}


@Composable
fun NewsLists(navController: NavController,viewModel: NewsListViewModel = hiltViewModel()){

      val newsList = viewModel.newsList
      val isLoading =  viewModel.isLoading
      val errorMessage = viewModel.errorMessage
      NewsLisView(newsies = newsList.value, navController =navController)

      Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()){

            if(isLoading.value){

                  CircularProgressIndicator()
            }
            if (errorMessage.value.isNotEmpty()){

                  RetryView(error = errorMessage.value) {
                        viewModel.loadNews()
                  }
            }


      }




}


@ExperimentalCoilApi
@Composable
fun newsRow(navController: NavController, news : Article){

      Column(verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(size = 15.dp))
                  .background(color = MaterialTheme.colors.secondary)

                  .clickable {

                        if (news.urlToImage != null && news.url != null) {
                              val encodedUrl = URLEncoder.encode(
                                    news.urlToImage,
                                    StandardCharsets.UTF_8.toString()
                              )


                              val encodedUrl2 =
                                    URLEncoder.encode(
                                          news.url,
                                          StandardCharsets.UTF_8.toString()
                                    )



                              navController.navigate("news_details_screen/${news.title}/${news.description}/${encodedUrl}/${encodedUrl2}")
                        }


                  }

      ) {


            Text(text = news.title!!,
                  modifier = Modifier.padding(10.dp,5.dp,10.dp,1.dp),
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colors.background

                  )


            Image(painter = rememberImagePainter(data = news.urlToImage), contentDescription = news.title,
                  modifier = Modifier
                        .padding(15.dp, 5.dp, 15.dp, 15.dp)
                        .size(450.dp, 220.dp),
            )
            Spacer(modifier = Modifier.height(10.dp))

      }
      Spacer(modifier = Modifier.height(25.dp))

}

@Composable
fun RetryView(error: String, onRetry:()-> Unit){

      Column {
            Text(error, color = Color.Red, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                  onClick = { onRetry() },
                  modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                  Text(text = "Retry")

            }
      }


}


