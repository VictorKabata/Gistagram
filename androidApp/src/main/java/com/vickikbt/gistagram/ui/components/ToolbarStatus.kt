package com.vickikbt.gistagram.ui.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import kotlinx.coroutines.delay

@Composable
fun StatusToolbar(
    modifier: Modifier = Modifier,
    userName: String,
    title: String? = userName,
    subTitle: String? = null,
    profilePicUrl: String = "https://avatars.githubusercontent.com/u/39780120?u=bb50900c4214570b711aca1da85a84209b79fed0&v=4",
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.onSurface
) {
    var progress by remember { mutableStateOf(0.0f) }
    val enabled by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = progress, key2 = enabled) {
        if (progress < 1.0f && enabled) {
            delay(100L)
            progress += 0.001f
        }
    }
    val progressAnimation: Float by animateFloatAsState(
        if (enabled) 1f else 0.0f,
        animationSpec = tween(
            durationMillis = 10000,
            delayMillis = 40,
            easing = LinearOutSlowInEasing
        )
    )

    /* val progressAnimation by animateFloatAsState(
         targetValue = 1f,
         animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
         visibilityThreshold = 0.001f
     )*/

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
            .height(56.dp)
            .padding(vertical = 6.dp)
            .background(backgroundColor),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .padding(horizontal = 12.dp)
                .clip(RoundedCornerShape(20.dp)),
            progress = progress,
            color = MaterialTheme.colors.onSurface,
            backgroundColor = Color.Gray
        )

        Spacer(modifier = Modifier.height(8.dp))

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
                    modifier = Modifier.size(40.dp),
                    image = profilePicPainter,
                    contentDescription = "Profile picture"
                ) {}

                FloatingActionButton(
                    modifier = Modifier
                        .border(1.dp, contentColor, CircleShape)
                        .size(16.dp)
                        .align(Alignment.BottomEnd),
                    onClick = {},
                    backgroundColor = backgroundColor,
                    contentColor = contentColor
                ) {
                    Icon(
                        modifier = Modifier.size(8.dp),
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Edit Status"
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.wrapContentHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = title ?: stringResource(R.string.username),
                    style = MaterialTheme.typography.h4,
                    color = contentColor,
                    fontSize = 12.sp,
                    letterSpacing = .5.sp,
                    lineHeight = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (!subTitle.isNullOrEmpty()) {
                    Text(
                        text = subTitle,
                        style = MaterialTheme.typography.h3,
                        color = contentColor.copy(alpha = .8f),
                        fontSize = 11.sp,
                        letterSpacing = .5.sp,
                        lineHeight = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
