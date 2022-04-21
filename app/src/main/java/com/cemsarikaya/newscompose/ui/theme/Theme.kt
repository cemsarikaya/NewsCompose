package com.cemsarikaya.newscompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(

    primary = BlueMunsell,
    background = RichBlack,
    surface = Alabaster,
    secondary = Bone,


)





private val LightColorPalette = lightColors(
    primary = BlueMunsell,
    background = RichBlack,
    surface = Alabaster,
    secondary = Bone,

   /* background = Color.White,
    //surface = Color.White,
    //onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,

    */


    )

@Composable
fun NewsComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette


    } else {
        LightColorPalette
    }


    MaterialTheme(

        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}