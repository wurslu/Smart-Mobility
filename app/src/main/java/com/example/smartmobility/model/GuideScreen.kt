package com.example.smartmobility.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.smartmobility.R
import com.example.smartmobility.general.components.ImageResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PopularCheckInItem(
    val title: String,
    val imageSize: Dp = 64.dp,
    val imageResource: ImageResource
)

data class RecommendedItineraries(
    val scenicSpotNum: Int,
    val totalKilometers: Int,
    val routeName: String,
    val scenicSpotList: List<String>
)

data class GuideScreenNearbyRecommendation(
    val name: String,
    val tag: String,
    val price: Int,
    val distance: Int,
    val imageVector: ImageVector
)

class GuideScreen : ViewModel() {
    private val _popularCheckInList = MutableStateFlow<List<PopularCheckInItem>>(
        listOf<PopularCheckInItem>(
            PopularCheckInItem(
                title = "龙井茶园",
                imageResource = ImageResource.ResourceImage(resId = R.drawable.longjingchayuan)
            ), PopularCheckInItem(
                title = "雷锋夜景",
                imageResource = ImageResource.ResourceImage(resId = R.drawable.leifengyejing)
            ), PopularCheckInItem(
                title = "曲院风荷",
                imageResource = ImageResource.ResourceImage(resId = R.drawable.quyuanfenghe)
            ), PopularCheckInItem(
                title = "断桥残雪",
                imageResource = ImageResource.ResourceImage(resId = R.drawable.duanqiaocanxue)
            )
        )
    )
    private val _recommendtaionItinerariesList = MutableStateFlow<List<RecommendedItineraries>>(
        listOf<RecommendedItineraries>(
            RecommendedItineraries(
                scenicSpotNum = 9,
                totalKilometers = 12,
                routeName = "西湖一日游",
                scenicSpotList = listOf<String>("雷峰塔", "白提", "苏堤春晓", "断桥残雪")
            ), RecommendedItineraries(
                scenicSpotNum = 4,
                totalKilometers = 5,
                routeName = "灵隐寺祈福之旅",
                scenicSpotList = listOf<String>("灵隐寺", "飞来峰", "永福禅寺")
            )
        )
    )
    private val _nearbyRecommendationList = MutableStateFlow<List<GuideScreenNearbyRecommendation>>(
        listOf<GuideScreenNearbyRecommendation>(
            GuideScreenNearbyRecommendation(
                name = "知味观",
                tag = "杭帮菜",
                price = 158,
                distance = 350,
                imageVector = Icons.Filled.Restaurant
            ), GuideScreenNearbyRecommendation(
                name = "西子湖四季酒店",
                tag = "五星酒店",
                price = 2680,
                distance = 500,
                imageVector = Icons.Filled.Hotel
            )
        )
    )
    val nearbyRecommendationList = _nearbyRecommendationList.asStateFlow()
    val recommendedItinerariesList = _recommendtaionItinerariesList.asStateFlow()
    val popularCheckInItemList = _popularCheckInList.asStateFlow()
}