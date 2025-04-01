package com.example.smartmobility

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddIcCall
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartmobility.model.Profile
import com.example.smartmobility.model.ProfileScreen
import kotlin.UInt

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileScreenViewModel: ProfileScreen = viewModel()
) {
    val person: Profile by profileScreenViewModel.profile.collectAsState()
    LazyColumn(modifier = modifier) {
        item {
            UserProfileHeader()
        }
        item {
            UserIdentifySection(username = person.username, userId = person.userId)
        }
        item {
            ProfileMetricsBar(
                collections = person.collections,
                foots = person.foots,
                coupons = person.coupons,
                integral = person.integral
            )
        }
        item {
            ProfileMenuSection()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UserProfileHeader(modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "我的", style = MaterialTheme.typography.titleMedium)
        Icon(
            imageVector = Icons.Filled.Settings,
            contentDescription = "settings",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
    HorizontalDivider(
        modifier = Modifier, color = DividerDefaults.color.copy(alpha = 0.2f)
    )
}


@Composable
private fun UserIdentifySection(
    username: String,
    userId: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.avatar),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(64.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(text = username)
            Text(
                text = "ID:$userId",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun ProfileMetricsBar(
    collections: UInt,
    foots: UInt,
    coupons: UInt,
    integral: UInt,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            MetricCounter(
                metricName = "收藏",
                metricCount = collections,
                modifier = Modifier.weight(1f)
            )
            MetricCounter(
                metricName = "足迹",
                metricCount = foots,
                modifier = Modifier.weight(1f)
            )
            MetricCounter(
                metricName = "优惠券",
                metricCount = coupons,
                modifier = Modifier.weight(1f)
            )
            MetricCounter(
                metricName = "积分",
                metricCount = integral,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun MetricCounter(
    metricName: String,
    metricCount: UInt,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = metricCount.toString(),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = metricName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}

sealed class ProfileMenuItemParam(val icon: ImageVector, val title: String) {
    object MyOrder :
        ProfileMenuItemParam(icon = Icons.Filled.ConfirmationNumber, title = "我的订单")

    object MyCollections :
        ProfileMenuItemParam(icon = Icons.Filled.Favorite, title = "我的收藏")

    object FrequentAddress :
        ProfileMenuItemParam(icon = Icons.Filled.LocationOn, title = "常用地址")

    object ContactCustomService :
        ProfileMenuItemParam(icon = Icons.Filled.AddIcCall, title = "联系客服")
}

@Composable
fun ProfileMenuSection(modifier: Modifier = Modifier) {
    val profileMenuItemParamList = listOf<ProfileMenuItemParam>(
        ProfileMenuItemParam.MyOrder,
        ProfileMenuItemParam.MyCollections,
        ProfileMenuItemParam.FrequentAddress,
        ProfileMenuItemParam.ContactCustomService,
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        profileMenuItemParamList.forEach { item ->
            ProfileMenuItem(
                icon = item.icon,
                title = item.title
            )
        }
    }
}

@Composable
private fun ProfileMenuItem(
    icon: ImageVector, title: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Text(title)
            }
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MaterialTheme {
        ProfileScreen()
    }
}