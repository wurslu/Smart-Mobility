package com.example.smartmobility.general.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class ImageResource {
    data class ResourceImage(@DrawableRes val resId: Int) : ImageResource()
    data class VectorImage(val imageVector: ImageVector) : ImageResource()
}

@Composable
fun MediaTitleItem(
    imageResource: ImageResource,
    imageSize: Dp = 64.dp,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        when (imageResource) {
            is ImageResource.VectorImage -> {
                Icon(
                    imageVector = imageResource.imageVector,
                    contentDescription = null,
                    modifier = Modifier.size(imageSize)
                )
            }

            is ImageResource.ResourceImage -> {
                Image(
                    painter = painterResource(imageResource.resId),
                    contentDescription = null,
                    modifier = Modifier.size(imageSize)
                )
            }
        }
        Text(
            text = title,
        )
    }
}