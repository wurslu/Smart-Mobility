package com.example.smartmobility

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsSubway
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartmobility.general.components.Title
import kotlin.String
import kotlin.collections.listOf


@Composable
fun TravelScreen(modifier: Modifier = Modifier) {
    val frequentRouteList = listOf<FrequentRouteParam>(
        FrequentRouteParam(
            origin = "公司",
            destination = "家",
            duration = 35,
            first = "地铁1号线",
            second = "公交7路"
        ), FrequentRouteParam(
            origin = "家",
            destination = "商场",
            duration = 25,
            first = "地铁2号线",
            second = "公交游5路"
        )
    )
    val nearbyStationParamList = listOf<NearbyStationParam>(
        NearbyStationParam("文三路学院路口", 350, listOf<String>("14路", "8路", "教育专线","K528路")),
        NearbyStationParam("文三路地铁站", 750, listOf<String>("164路", "217路", "9路"))
    )


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        item {
            RouteOriginDestination()
        }
        item {
            FrequentRouteSection(frequentRouteList = frequentRouteList)
        }
        item {
            NearbyStationSection(nearbyStationParamList = nearbyStationParamList)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RouteOriginDestination(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = modifier
    ) {
        TextField(value = "",
            onValueChange = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Circle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.scale(0.5f)
                )
            },
            colors = TextFieldDefaults.colors().copy(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = { Text("您在哪上车？", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier.weight(0.4f)
        )
        IconButton(
            onClick = {}, modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Default.SwapHoriz,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxSize()
            )
        }
        TextField(value = "",
            onValueChange = { },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                )
            },
            colors = TextFieldDefaults.colors().copy(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            shape = MaterialTheme.shapes.medium,
            placeholder = { Text("您要去哪？", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier.weight(0.4f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TransportMode(
    modifier: Modifier = Modifier
) {
    val transportModeItemList = listOf<TransportModeItem>(
        TransportModeItem(icon = Icons.Default.DirectionsBus, title = "公交"),
        TransportModeItem(icon = Icons.Default.DirectionsSubway, title = "地铁"),
        TransportModeItem(icon = Icons.Default.PedalBike, title = "单车"),
        TransportModeItem(icon = Icons.Default.DirectionsCar, title = "出租"),
        TransportModeItem(icon = Icons.Default.MoreHoriz, title = "更多")
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()
    ) {
        transportModeItemList.forEach { item ->
            TransportModeCard(item)
        }
    }
}

data class TransportModeItem(
    val icon: ImageVector, val title: String
)

@Composable
fun TransportModeCard(
    transportModeItem: TransportModeItem, modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                imageVector = transportModeItem.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )

        }
        Text(text = transportModeItem.title)
    }
}

data class FrequentRouteParam(
    val origin: String,
    val destination: String,
    val duration: Int,
    val first: String,
    val second: String
)

@Composable
private fun FrequentRouteSection(
    frequentRouteList: List<FrequentRouteParam>, modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Title("常用路线")
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            frequentRouteList.forEach { item ->
                FrequentRouteCard(item)
            }
        }

    }
}

@Composable
private fun FrequentRouteCard(
    frequentRouteParam: FrequentRouteParam
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.secondary,
                            modifier = Modifier.scale(0.6f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = frequentRouteParam.origin)
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = frequentRouteParam.destination)
                    }
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        "约${frequentRouteParam.duration}分钟",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "实时出发",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                RouteItem(frequentRouteParam.first)
                RouteItem(frequentRouteParam.second)
            }
        }
    }
}

@Composable
private fun RouteItem(text: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(4.dp)
        )
    }
}

data class NearbyStationParam(
    val station: String, val distance: Int, val routeList: List<String>
)

@Composable
private fun NearbyStationSection(
    nearbyStationParamList: List<NearbyStationParam>, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Title("附近站点")
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            nearbyStationParamList.forEach { nearbyStationParam ->
                NearbyStationCard(nearbyStationParam)
            }
        }
    }
}

@Composable
private fun NearbyStationCard(
    nearbyStationParam: NearbyStationParam,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsBus,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Column {
                        Text(
                            nearbyStationParam.station,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                        Text(
                            "距离 ${nearbyStationParam.distance}m",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                        )
                    }
                }
                OutlinedButton(border = BorderStroke(
                    width = 1.dp, color = MaterialTheme.colorScheme.primary
                ), onClick = {}) {
                    Text("导航", color = MaterialTheme.colorScheme.primary)
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                nearbyStationParam.routeList.forEach { item ->
                    RouteItem(text = item)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TravelScreenPreview() {
    MaterialTheme {
        TravelScreen()
    }
}