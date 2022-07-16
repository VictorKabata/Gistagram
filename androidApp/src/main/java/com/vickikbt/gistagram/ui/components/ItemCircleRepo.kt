package com.vickikbt.gistagram.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.vickikbt.gistagram.R

@Composable
fun ItemCircleRepo(
    modifier: Modifier = Modifier,
    image: Painter = painterResource(id = R.drawable.ic_logo),
    contentDescription: String,
    tintColor: Color? = null,
    borderColor: Color? = null,
    backgroundColor: Color? = null
) {

    Icon(
        modifier = modifier
            .padding(3.dp)
            .clip(CircleShape)
            .background(
                backgroundColor ?: borderColor?.copy(alpha = .5f) ?: MaterialTheme.colors.surface
            )
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(width = 2.dp, color = borderColor ?: Color.Gray, shape = CircleShape),
        painter = image,
        contentDescription = contentDescription,
        tint = tintColor ?: MaterialTheme.colors.onSurface
    )
}
