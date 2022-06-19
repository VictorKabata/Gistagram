package com.vickikbt.gistagram.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.vickikbt.gistagram.R

@Composable
fun StatusToolbar(
    modifier: Modifier = Modifier,
    userName: String? = "VictorKabata",
    profilePicUrl: String = "https://avatars.githubusercontent.com/u/39780120?u=bb50900c4214570b711aca1da85a84209b79fed0&v=4",
    backgroundColor:Color=MaterialTheme.colors.surface,
    contentColor:Color=MaterialTheme.colors.onSurface
) {
    var progress by remember { mutableStateOf(0f) }
    var indicatorProgress = 1f
    val progressAnimDuration = 10000
    val progressAnimation by animateFloatAsState(
        targetValue = indicatorProgress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(indicatorProgress) {
        progress = indicatorProgress
    }

    val profilePicPainter = rememberImagePainter(
        data = profilePicUrl,
        builder = {
            crossfade(true)
            placeholder(R.drawable.ic_profile)
            transformations(CircleCropTransformation())
        }
    )


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(backgroundColor),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .clip(RoundedCornerShape(20.dp)),
            progress = progressAnimation,
            color = MaterialTheme.colors.onSurface,
            backgroundColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Box {
                ItemCircleImage(
                    modifier = Modifier.size(20.dp),
                    image = profilePicPainter,
                    contentDescription = "Profile picture"
                ) {}

                FloatingActionButton(
                    modifier = Modifier
                        .size(12.dp)
                        .align(Alignment.BottomEnd)
                        .border(2.dp, backgroundColor),
                    onClick = {},
                    backgroundColor = contentColor,
                    contentColor = backgroundColor
                ) {
                    Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Edit Status")
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = userName ?: stringResource(R.string.username),
                style = MaterialTheme.typography.h3,
                color = contentColor,
                fontSize = 12.sp,
                letterSpacing = .5.sp,
                lineHeight = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
