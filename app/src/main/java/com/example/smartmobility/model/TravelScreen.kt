package com.example.smartmobility.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class FrequentRoute(
    val origin: String,
    val destination: String,
    val duration: Int,
    val first: String,
    val second: String
)

data class NearbyStation(
    val station: String, val distance: Int, val routeList: List<String>
)

class TravelScreen : ViewModel() {
    private val _frequentRouteList = MutableStateFlow<List<FrequentRoute>>(
        listOf<FrequentRoute>(
            FrequentRoute(
                origin = "公司",
                destination = "家",
                duration = 35,
                first = "地铁1号线",
                second = "公交7路"
            ), FrequentRoute(
                origin = "家",
                destination = "商场",
                duration = 25,
                first = "地铁2号线",
                second = "公交游5路"
            )
        )
    )
    private val _nearbyStationList = MutableStateFlow<List<NearbyStation>>(
        listOf<NearbyStation>(
            NearbyStation(
                "文三路学院路口",
                350,
                listOf<String>("14路", "8路", "教育专线", "K528路")
            ),
            NearbyStation("文三路地铁站", 750, listOf<String>("164路", "217路", "9路"))
        )
    )

    val frequentRouteList = _frequentRouteList.asStateFlow()
    val nearbyRecommendationList = _nearbyStationList.asStateFlow()
}