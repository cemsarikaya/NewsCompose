package com.cemsarikaya.newscompose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import coil.annotation.ExperimentalCoilApi
import com.cemsarikaya.newscompose.ui.theme.NewsComposeTheme
import com.cemsarikaya.newscompose.view.webView
import com.cemsarikaya.newscompose.view.NewsDetailsScreen
import com.cemsarikaya.newscompose.view.NewsListScreen
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MobileAds.initialize(this) {}
            NewsComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "news_list_screen"){

                    composable("news_list_screen"){

                       NewsListScreen(navController = navController)

                    }
                    composable("webView/{url}",arguments = listOf(
                        navArgument("url"){
                            type = NavType.StringType
                        }

                    )){
                        val url =remember{

                            it.arguments?.getString("url")

                        }
                        webView(url = url?:"",navController = navController)
                    }


                    composable("news_details_screen/{newsTitle}/{newsContent}/{image}/{url}",arguments = listOf(

                       navArgument("newsTitle"){
                            type = NavType.StringType

                        },
                        navArgument("newsContent"){

                            type = NavType.StringType
                        } ,
                        navArgument("image"){

                            type = NavType.StringType
                        } ,
                        navArgument("url"){

                            type =NavType.StringType
                        }


                    )){
                        val newsTitle = remember{
                            it.arguments?.getString("newsTitle")
                        }
                        val newsContent = remember{

                            it.arguments?.getString("newsContent")
                        }

                        val image = remember{

                            it.arguments?.getString("image")
                        }
                        val url =remember{


                            it.arguments?.getString("url")

                        }




                        NewsDetailsScreen(title = newsTitle?:"",content = newsContent?:"",image=image?:"",url=url?:"", navController = navController)


                    }


                }


                }
            }
        }
    }


