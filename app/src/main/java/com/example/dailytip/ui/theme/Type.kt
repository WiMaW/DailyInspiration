package com.example.dailytip.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.sp
import com.example.dailytip.R


// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = DancingScriptRegular,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = TajwalMedium,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = TajwalLight,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = TajwalMedium,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp)
)

val dancingScriptFont = FontFamily(
    Font(R.font.dancingscript_regular)
)