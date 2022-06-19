package com.vickikbt.gistagram.ui.screens.profile.tabs

import android.graphics.Color.parseColor
import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.ui.components.ItemCircleRepo

@Composable
fun ItemPinnedRepo(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.ic_logo,
    pinnedRepo: LoggedInUserProfileQuery.OnRepository?,
    onItemClicked: (String) -> Unit
) {

    Column(
        // modifier = modifier.clickable { onItemClicked(pinnedRepo?.id!!) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ItemCircleRepo(
            modifier = Modifier
                .size(64.dp)
                .clickable {
                    pinnedRepo?.id?.let { onItemClicked(it) }
                },
            image = painterResource(id = image),
            contentDescription = pinnedRepo?.name ?: stringResource(R.string.pinned_repo),
            borderColor = Color(parseColor(pinnedRepo?.languages?.nodes?.get(0)?.color))
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            modifier = Modifier.width(70.dp),
            text = pinnedRepo?.name ?: stringResource(id = R.string.pinned_repo),
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )
    }
}
