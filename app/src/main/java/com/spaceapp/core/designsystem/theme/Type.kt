package com.spaceapp.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.spaceapp.R

val fonts = FontFamily(
    Font(R.font.proportional_tfb)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        letterSpacing = 1.15.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Light,
        fontSize = 22.sp,
        letterSpacing = 1.15.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        letterSpacing = 1.15.sp
    ),
    displayMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 1.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 1.15.sp
    ) ,
    labelMedium = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.15.sp
    )
)