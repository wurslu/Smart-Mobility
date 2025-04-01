package com.example.smartmobility

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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartmobility.general.components.ImageResource
import com.example.smartmobility.general.components.MediaTitleItem
import com.example.smartmobility.general.components.Title
import com.example.smartmobility.model.HomeScreen
import com.example.smartmobility.model.HomeScreenNearbyRecommendation
import com.example.smartmobility.model.TravelRecommendation


@Composable
fun HomeScreen(modifier: Modifier = Modifier, helloScreenViewModel: HomeScreen = viewModel()) {
    val travelRecommendationList by helloScreenViewModel.travelRecommendationList.collectAsState()
    val nearbyRecommendationList by helloScreenViewModel.nearbyRecommendationList.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item { Map() }
        item { TransportModeNavBar() }
        item { TravelRecommendationSection(recommendationCardList = travelRecommendationList) }
        item { NearbyRecommendationSection(recommendationList = nearbyRecommendationList) }
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
            MediaTitleItem(
                title = item.title, imageResource = ImageResource.ResourceImage(resId = item.image)
            )
        }
    }
}


@Composable
private fun TravelRecommendationSection(
    recommendationCardList: List<TravelRecommendation>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
    ) {
        Title("出行推荐")
        recommendationCardList.forEach { recommendation ->
            TravelRecommendationCard(
                recommendation = recommendation,
            )
        }
    }
}


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
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(14.dp)
            )
            Text(
                text = recommendation.routeOriginDestination,
                style = MaterialTheme.typography.bodyMedium,
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
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


@Composable
private fun NearbyRecommendationSection(
    recommendationList: List<HomeScreenNearbyRecommendation>,
    modifier: Modifier = Modifier,
) {


    Column(
        modifier = modifier
    ) {
        Title("周边推荐")
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
    recommendation: HomeScreenNearbyRecommendation,
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
    MaterialTheme {
        HomeScreen()
    }
}