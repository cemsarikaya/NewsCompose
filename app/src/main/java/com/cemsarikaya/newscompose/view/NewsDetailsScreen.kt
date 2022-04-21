package com.cemsarikaya.newscompose.view
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.cemsarikaya.AdMobView.AdMobScreen
import com.cemsarikaya.newscompose.viewmodel.NewsDetailsViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@ExperimentalCoilApi
@Composable
fun NewsDetailsScreen(title:String,content:String,image:String,url:String,navController: NavController, viewModel: NewsDetailsViewModel = hiltViewModel()){

  /*  val news = produceState<Resource<NewsList>>(initialValue =Resource.Loading()){

        value = viewModel.getNews()

    }.value

   */


        Surface(color = MaterialTheme.colors.surface,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )) {


            Column(Modifier.background(color = MaterialTheme.colors.secondary),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Column(Modifier.background(color = MaterialTheme.colors.secondary))
                {

                    Column(modifier=Modifier.background(color = MaterialTheme.colors.primary)) {



                        Text(text = "HABERLER", modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(size = 10.dp)),
                            textAlign = TextAlign.Center,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.background

                        )


                    }

                    }


                    //when (news) {

                       // is Resource.Success -> {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.padding(15.dp),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.background

                            )

                            Image(
                                painter = rememberImagePainter(data = image),
                                contentDescription = title,
                                modifier = Modifier
                                    .padding(15.dp, 5.dp, 15.dp, 15.dp)
                                    .size(450.dp, 220.dp)
                            )
                            //admob
                           AdMobScreen()

                            Text(
                                text = content,
                                fontSize= 20.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(15.dp),
                                color = MaterialTheme.colors.background

                            )

                            Spacer(modifier = Modifier.height(25.dp))


                            Box(
                                contentAlignment = Alignment.BottomEnd,
                                modifier = Modifier.padding(25.dp)
                            ) {

                                val encodedUrl =
                                URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                                    ExtendedFloatingActionButton(
                                    onClick = {navController.navigate("webView/${encodedUrl}") },
                                    backgroundColor = MaterialTheme.colors.primary,
                                    text = { Text("Web Sitesinde Görüntüle") },
                                    contentColor = Color.White
                                )

                            }


                     //   }

/*
                        is Resource.Loading -> {

                            CircularProgressIndicator(color = MaterialTheme.colors.primary)
                        }

                        is Resource.Error -> {
                            Text(text = news.message!!)

                        }

                        */
                    }



                }



            }











