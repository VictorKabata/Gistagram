package com.vickikbt.gistagram.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ItemCircleImage(
    modifier: Modifier = Modifier,
    image: Painter,
    contentDescription: String,
    borderColor: Color? = null
) {

    Image(
        modifier = modifier
            .padding(3.dp)
            .clip(CircleShape)
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(width = 1.dp, color = borderColor ?: Color.Gray, shape = CircleShape),
        painter = image,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )

}