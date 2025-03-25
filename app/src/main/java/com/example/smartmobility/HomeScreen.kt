package com.example.smartmobility

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item { Map() }
        item { TransportModeNavBar() }
        item { TravelRecommendationSection() }
        item { NearbyRecommendationSection() }
    }
}

@Composable
private fun Map(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.map_img_occupy_space),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.fillMaxWidth()
    )
}

sealed class TransportMode(
    val title: String, val image: Int
) {
    object BusTransit :
        TransportMode(title = "实时公交", image = R.drawable.transportmodenavitem_bustransit)

    object MetroTransit :
        TransportMode(title = "地铁出行", image = R.drawable.transportmodenavitem_metrotransit)

    object ScenicSpot :
        TransportMode(title = "景区导览", image = R.drawable.transportmodenavitem_scenicspot)

    object BikeSharing :
        TransportMode(title = "共享单车", image = R.drawable.transportmodenavitem_bikesharing)
}

@Composable
fun TransportModeNavBar(modifier: Modifier = Modifier) {
    val transportModeNavItemList = listOf<TransportMode>(
        TransportMode.BusTransit,
        TransportMode.MetroTransit,
        TransportMode.ScenicSpot,
        TransportMode.BikeSharing
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()
    ) {
        transportModeNavItemList.forEach { item ->
            TransportModeNavItem(
                title = item.title, image = item.image
            )
        }
    }
}

@Composable
fun TransportModeNavItem(
    @DrawableRes image: Int, title: String, modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun TravelRecommendationSection(modifier: Modifier = Modifier) {
    val recommendationCardList = listOf<TravelRecommendation>(
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
    Column(
        modifier = modifier
    ) {
        Text(
            text = "出行推荐",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        recommendationCardList.forEach { recommendation ->
            TravelRecommendationCard(
                recommendation = recommendation,
            )
        }
    }
}

data class TravelRecommendation(
    val routeOriginDestination: String, val routeDuration: Int, val routeDetail: String
)

@Composable
private fun TravelRecommendationCard(
    recommendation: TravelRecommendation,
    modifier: Modifier = Modifier,
) {
    @Composable
    fun RouteOriginDestination(modifier: Modifier = Modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier
        ) {
            Image(
                painter = painterResource(R.drawable.travelrecommendationcard_icon),
                contentDescription = null,
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = recommendation.routeOriginDestination,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
        }
    }

    @Composable
    fun RouteDuration() {
        Text(
            "约${recommendation.routeDuration}分钟",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()

            ) {
                RouteOriginDestination()
                RouteDuration()
            }
            Text(
                recommendation.routeDetail,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}


data class NearbyRecommendation(
    val attractionImage: Int,
    val attractionName: String,
)


@Composable
private fun NearbyRecommendationSection(
    modifier: Modifier = Modifier,
) {
    val recommendationList = listOf<NearbyRecommendation>(
        NearbyRecommendation(
            attractionImage = R.drawable.nearbyrecommendation_first, attractionName = "断桥残雪"
        ), NearbyRecommendation(
            attractionImage = R.drawable.nearbyrecommendation_second, attractionName = "灵隐寺"
        )
    )

    Column(
        modifier = modifier
    ) {
        Text(
            text = "周边推荐",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NearbyRecommendationCard(recommendationList[0], modifier = Modifier.weight(0.5f))
            NearbyRecommendationCard(recommendationList[1], modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
fun NearbyRecommendationCard(
    recommendation: NearbyRecommendation,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(recommendation.attractionImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Text(
            recommendation.attractionName,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}