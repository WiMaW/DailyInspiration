package com.example.dailytip.Data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.dailytip.R

data class DailyTip(
    @DrawableRes val dailyImage: Int,
    val dayNumber: String,
    @StringRes val tip: Int
)

val dailyList = listOf<DailyTip>(
    DailyTip(R.drawable.tip_one, "1", R.string.tip_one),
    DailyTip(R.drawable.tip_two, "2", R.string.tip_two),
    DailyTip(R.drawable.tip_three, "3", R.string.tip_three),
    DailyTip(R.drawable.tip_four, "4", R.string.tip_four),
    DailyTip(R.drawable.tip_five, "5", R.string.tip_five),
    DailyTip(R.drawable.tip_six, "6", R.string.tip_six),
    DailyTip(R.drawable.tip_seven, "7", R.string.tip_seven),
    DailyTip(R.drawable.tip_eight, "8", R.string.tip_eight),
    DailyTip(R.drawable.tip_nine, "9", R.string.tip_nine),
    DailyTip(R.drawable.tip_ten, "10", R.string.tip_ten),
    DailyTip(R.drawable.tip_eleven, "11", R.string.tip_eleven),
    DailyTip(R.drawable.tip_twelve, "12", R.string.tip_twelve),
    DailyTip(R.drawable.tip_thirteen, "13", R.string.tip_thirteen),
    DailyTip(R.drawable.tip_fourteen, "14", R.string.tip_fourteen),
    DailyTip(R.drawable.tip_fifteen, "15", R.string.tip_fifteen),
    DailyTip(R.drawable.tip_sixteen, "16", R.string.tip_sixteen),
    DailyTip(R.drawable.tip_seventeen, "17", R.string.tip_seventeen),
    DailyTip(R.drawable.tip_eighteen, "18", R.string.tip_eighteen),
    DailyTip(R.drawable.tip_nineteen, "19", R.string.tip_nineteen),
    DailyTip(R.drawable.tip_twenty, "20", R.string.tip_twenty),
    DailyTip(R.drawable.tip_twenty_one, "21", R.string.tip_twenty_one),
    DailyTip(R.drawable.tip_twenty_two, "22", R.string.tip_twenty_two),
    DailyTip(R.drawable.tip_twenty_three, "23", R.string.tip_twenty_three),
    DailyTip(R.drawable.tip_twenty_four, "24", R.string.tip_twenty_four),
    DailyTip(R.drawable.tip_twenty_five, "25", R.string.tip_twenty_five),
    DailyTip(R.drawable.tip_twenty_six, "26", R.string.tip_twenty_six),
    DailyTip(R.drawable.tip_twenty_seven, "27", R.string.tip_twenty_seven),
    DailyTip(R.drawable.tip_twenty_eight, "28", R.string.tip_twenty_eight),
    DailyTip(R.drawable.tip_twenty_nine, "29", R.string.tip_twenty_nine),
    DailyTip(R.drawable.tip_thirty, "30", R.string.tip_thirty)
)