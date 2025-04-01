package com.example.smartmobility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartmobility.general.components.MediaTitleItem
import com.example.smartmobility.general.components.Title
import com.example.smartmobility.model.GuideScreen
import com.example.smartmobility.model.GuideScreenNearbyRecommendation
import com.example.smartmobility.model.PopularCheckInItem
import com.example.smartmobility.model.RecommendedItineraries


@Composable
fun GuideScreen(modifier: Modifier = Modifier, guideScreenViewModel: GuideScreen = viewModel()) {
    val popularCheckInItemList by guideScreenViewModel.popularCheckInItemList.collectAsState()
    val recommendedItinerariesList by guideScreenViewModel.recommendedItinerariesList.collectAsState()
    val nearbyRecommendationList by guideScreenViewModel.nearbyRecommendationList.collectAsState()

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        item {
            ScenicSpotGallery()
        }
        item {
            PopularCheckInSection(itemList = popularCheckInItemList)
        }
        item {
            RecommendedItinerariesSection(recommendedItinerariesCardParamList = recommendedItinerariesList)
        }
        item {
            NearbyRecommendationSection(nearbyRecommendationCardParamList = nearbyRecommendationList)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScenicSpotGallery(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.guide_screen_scenic_spot_gallery_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(R.drawable.guide_screen_scenic_spot_gallery_2),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun PopularCheckInSection(
    itemList: List<PopularCheckInItem>, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Title("热门打卡")
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
        ) {
            itemList.forEach { item ->
                MediaTitleItem(
                    title = item.title,
                    imageResource = item.imageResource,
                    imageSize = item.imageSize
                )
            }
        }
    }
}


@Composable
private fun RecommendedItinerariesSection(
    recommendedItinerariesCardParamList: List<RecommendedItineraries>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Title("推荐路线")
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            recommendedItinerariesCardParamList.forEach { item ->
                RecommendedItinerariesCard(recommendedItinerariesCardParam = item)
            }
        }
    }
}

@Composable
private fun RecommendedItinerariesCard(
    recommendedItinerariesCardParam: RecommendedItineraries,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        recommendedItinerariesCardParam.routeName,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        "含${recommendedItinerariesCardParam.scenicSpotNum}个景点 · 全程${recommendedItinerariesCardParam.totalKilometers}公里",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                OutlinedButton(
                    onClick = {},
                    border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                ) {
                    Text("导航", color = MaterialTheme.colorScheme.primary)
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                recommendedItinerariesCardParam.scenicSpotList.forEach { item ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
                    ) {
                        Text(
                            item,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun NearbyRecommendationSection(
    nearbyRecommendationCardParamList: List<GuideScreenNearbyRecommendation>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Title("周边推荐")
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            nearbyRecommendationCardParamList.forEach { item ->
                NearbyRecommendationCard(item)
            }
        }
    }
}

@Composable
private fun NearbyRecommendationCard(
    nearbyRecommendationCardParam: GuideScreenNearbyRecommendation,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    imageVector = nearbyRecommendationCardParam.imageVector,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Column {
                    Text(
                        nearbyRecommendationCardParam.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        "${nearbyRecommendationCardParam.tag} · 人均 ¥${nearbyRecommendationCardParam.price}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
            Text(
                "距离 ${nearbyRecommendationCardParam.distance}m",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuideScreenPreview() {
    MaterialTheme {
        GuideScreen()
    }
}