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
    DailyTip(R.drawable.tip_twenty_five, "2", R.string.tip_two),
    DailyTip(R.drawable.tip_three, "3", R.string.tip_three),
    DailyTip(R.drawable.tip_sixteen, "4", R.string.tip_four),
    DailyTip(R.drawable.tip_twenty_seven, "5", R.string.tip_five),
    DailyTip(R.drawable.tip_twenty_one, "6", R.string.tip_six),
    DailyTip(R.drawable.tip_twenty_three, "7", R.string.tip_seven),
    DailyTip(R.drawable.tip_two, "8", R.string.tip_eight),
    DailyTip(R.drawable.tip_twenty_two, "9", R.string.tip_nine),
    DailyTip(R.drawable.tip_twenty_eight, "10", R.string.tip_ten),
    DailyTip(R.drawable.tip_twenty_one, "11", R.string.tip_eleven),
    DailyTip(R.drawable.tip_sixteen, "12", R.string.tip_twelve),
    DailyTip(R.drawable.tip_twenty_six, "13", R.string.tip_thirteen),
    DailyTip(R.drawable.tip_twenty_eight, "14", R.string.tip_fourteen),
    DailyTip(R.drawable.tip_twenty_two, "15", R.string.tip_fifteen),
    DailyTip(R.drawable.tip_sixteen, "16", R.string.tip_sixteen),
    DailyTip(R.drawable.tip_one, "17", R.string.tip_seventeen),
    DailyTip(R.drawable.tip_twenty_five, "18", R.string.tip_eighteen),
    DailyTip(R.drawable.tip_twenty_eight, "19", R.string.tip_nineteen),
    DailyTip(R.drawable.tip_twenty_four, "20", R.string.tip_twenty),
    DailyTip(R.drawable.tip_twenty_one, "21", R.string.tip_twenty_one),
    DailyTip(R.drawable.tip_twenty_two, "22", R.string.tip_twenty_two),
    DailyTip(R.drawable.tip_twenty_three, "23", R.string.tip_twenty_three),
    DailyTip(R.drawable.tip_twenty_four, "24", R.string.tip_twenty_four),
    DailyTip(R.drawable.tip_twenty_five, "25", R.string.tip_twenty_five),
    DailyTip(R.drawable.tip_twenty_six, "26", R.string.tip_twenty_six),
    DailyTip(R.drawable.tip_twenty_seven, "27", R.string.tip_twenty_seven),
    DailyTip(R.drawable.tip_twenty_two, "28", R.string.tip_twenty_eight),
    DailyTip(R.drawable.tip_twenty_five, "29", R.string.tip_twenty_nine),
    DailyTip(R.drawable.tip_sixteen, "30", R.string.tip_thirty)
)


