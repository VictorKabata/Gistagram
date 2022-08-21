package com.vickikbt.gistagram.ui.components.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemBioText(
    modifier: Modifier = Modifier,
    textColor: Color? = null,
    @DrawableRes imageRes: Int,
    text: String
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(id = imageRes),
            contentDescription = text,
            tint = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = textColor ?: MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            letterSpacing = 0.5.sp,
        )
    }
}
