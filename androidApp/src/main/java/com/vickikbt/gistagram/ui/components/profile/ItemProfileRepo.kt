package com.vickikbt.gistagram.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.gistagram.R

@ExperimentalMaterialApi
@Composable
fun ItemProfileRepo(
    modifier: Modifier = Modifier,
    repo: LoggedInUserProfileQuery.Node3?,
    onItemClicked: (String) -> Unit
) {

    val painter = rememberImagePainter(data = repo?.owner?.onUser?.avatarUrl) {
        placeholder(R.drawable.ic_profile)
        crossfade(true)
    }

    Card(
        modifier = modifier,
        onClick = { onItemClicked(repo?.id!!) },
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp
    ) {
        Column(Modifier.padding(vertical = 10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .weight(.2f),
                    painter = painter,
                    contentDescription = stringResource(id = R.string.profile_picture)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.8f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = repo?.name ?: stringResource(R.string.repository),
                        style = MaterialTheme.typography.h5.copy(fontSize = 18.sp),
                        maxLines = 1,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onBackground
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        repo?.languages?.nodes?.forEach {
                            Text(
                                text = it?.name ?: stringResource(R.string.language),
                                style = MaterialTheme.typography.body1,
                                maxLines = 1,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                modifier = Modifier.padding(horizontal = 13.dp),
                text = repo?.description ?: stringResource(R.string.no_description),
                style = MaterialTheme.typography.body1,
                maxLines = 4,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}
