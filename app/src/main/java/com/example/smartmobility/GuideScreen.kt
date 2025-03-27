package com.example.smartmobility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Hotel
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartmobility.general.components.ImageResource
import com.example.smartmobility.general.components.MediaTitleItem
import com.example.smartmobility.general.components.MediaTitleItemParam
import com.example.smartmobility.general.components.Title


@Composable
fun GuideScreen(modifier: Modifier = Modifier) {
    val popularCheckInSectionItemList = listOf<MediaTitleItemParam>(
        MediaTitleItemParam(
            title = "龙井茶园",
            imageResource = ImageResource.ResourceImage(resId = R.drawable.longjingchayuan)
        ), MediaTitleItemParam(
            title = "雷锋夜景",
            imageResource = ImageResource.ResourceImage(resId = R.drawable.leifengyejing)
        ), MediaTitleItemParam(
            title = "曲院风荷",
            imageResource = ImageResource.ResourceImage(resId = R.drawable.quyuanfenghe)
        ), MediaTitleItemParam(
            title = "断桥残雪",
            imageResource = ImageResource.ResourceImage(resId = R.drawable.duanqiaocanxue)
        )
    )
    val recommendedItinerariesCardParamList = listOf<RecommendedItinerariesCardParam>(
        RecommendedItinerariesCardParam(
            scenicSpotNum = 9,
            totalKilometers = 12,
            routeName = "西湖一日游",
            scenicSpotList = listOf<String>("雷峰塔", "白提", "苏堤春晓", "断桥残雪")
        ), RecommendedItinerariesCardParam(
            scenicSpotNum = 4,
            totalKilometers = 5,
            routeName = "灵隐寺祈福之旅",
            scenicSpotList = listOf<String>("灵隐寺", "飞来峰", "永福禅寺")
        )
    )
    val nearbyRecommendationCardParamList = listOf<NearbyRecommendationCardParam>(
        NearbyRecommendationCardParam(
            name = "知味观",
            tag = "杭帮菜",
            price = 158,
            distance = 350,
            imageVector = Icons.Filled.Restaurant
        ), NearbyRecommendationCardParam(
            name = "西子湖四季酒店",
            tag = "五星酒店",
            price = 2680,
            distance = 500,
            imageVector = Icons.Filled.Hotel
        )
    )

    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        item {
            ScenicSpotGallery()
        }
        item {
            PopularCheckInSection(itemList = popularCheckInSectionItemList)
        }
        item {
            RecommendedItinerariesSection(recommendedItinerariesCardParamList = recommendedItinerariesCardParamList)
        }
        item {
            NearbyRecommendationSection(nearbyRecommendationCardParamList = nearbyRecommendationCardParamList)
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
    itemList: List<MediaTitleItemParam>, modifier: Modifier = Modifier
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

data class RecommendedItinerariesCardParam(
    val scenicSpotNum: Int,
    val totalKilometers: Int,
    val routeName: String,
    val scenicSpotList: List<String>
)

@Composable
private fun RecommendedItinerariesSection(
    recommendedItinerariesCardParamList: List<RecommendedItinerariesCardParam>,
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
    recommendedItinerariesCardParam: RecommendedItinerariesCardParam,
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

data class NearbyRecommendationCardParam(
    val name: String,
    val tag: String,
    val price: Int,
    val distance: Int,
    val imageVector: ImageVector
)

@Composable
private fun NearbyRecommendationSection(
    nearbyRecommendationCardParamList: List<NearbyRecommendationCardParam>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Title("推荐路线")
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
    nearbyRecommendationCardParam: NearbyRecommendationCardParam,
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