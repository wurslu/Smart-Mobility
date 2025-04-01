package com.example.smartmobility.model

import androidx.lifecycle.ViewModel
import com.example.smartmobility.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TravelRecommendation(
    val routeOriginDestination: String,
    val routeDuration: Int,
    val routeDetail: String
)

data class HomeScreenNearbyRecommendation(
    val attractionImage: Int,
    val attractionName: String,
)

class HomeScreen : ViewModel() {
    private val _travelRecommendationList = MutableStateFlow<List<TravelRecommendation>>(
        listOf<TravelRecommendation>(
            TravelRecommendation(
                routeOriginDestination = "西湖文化广场 → 灵隐寺",
                routeDuration = 35,
                routeDetail = "地铁1号线 → 公交7路"
            ), TravelRecommendation(
                routeOriginDestination = "武林广场 → 西湖景区",
                routeDuration = 25,
                routeDetail = "地铁2号线 → 公交游5路"
            )
        )
    )
    private val _nearbyRecommendationList = MutableStateFlow<List<HomeScreenNearbyRecommendation>>(
        listOf<HomeScreenNearbyRecommendation>(
            HomeScreenNearbyRecommendation(
                attractionImage = R.drawable.nearbyrecommendation_first, attractionName = "断桥残雪"
            ), HomeScreenNearbyRecommendation(
                attractionImage = R.drawable.nearbyrecommendation_second, attractionName = "灵隐寺"
            )
        )
    )

    val travelRecommendationList = _travelRecommendationList.asStateFlow()
    val nearbyRecommendationList = _nearbyRecommendationList.asStateFlow()
}