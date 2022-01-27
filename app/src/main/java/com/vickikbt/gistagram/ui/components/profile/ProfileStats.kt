package com.vickikbt.gistagram.ui.components.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.UserProfileQuery

@Composable
fun ProfileStats(modifier: Modifier = Modifier, user: UserProfileQuery.User?) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ItemProfileStat(
            statValue = user?.repositories?.totalCount ?: 0,
            statTitle = R.string.repos,
            onItemClicked = {}
        )

        ItemProfileStat(
            statValue = user?.followers?.totalCount ?: 0,
            statTitle = R.string.followers,
            onItemClicked = {}
        )

        ItemProfileStat(
            statValue = user?.following?.totalCount ?: 0,
            statTitle = R.string.following,
            onItemClicked = {}
        )
    }

}

@Composable
fun ItemProfileStat(
    modifier: Modifier = Modifier,
    statValue: Int,
    @StringRes statTitle: Int,
    onItemClicked: () -> Unit?
) {

    Column(
        modifier = modifier.clickable { onItemClicked },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = statValue.toString(),
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            fontSize = 20.sp,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = statTitle),
            style = MaterialTheme.typography.h4,
            maxLines = 1,
            fontSize = 12.sp,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )

    }

}