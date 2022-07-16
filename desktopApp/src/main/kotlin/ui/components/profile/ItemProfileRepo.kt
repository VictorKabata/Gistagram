package ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import utils.loadImageBitmap

@ExperimentalMaterialApi
@Composable
fun ItemProfileRepo(
    modifier: Modifier = Modifier,
    repo: LoggedInUserProfileQuery.Node3?,
    onItemClicked: (String) -> Unit
) {

    val painter by remember {
        mutableStateOf(
            loadImageBitmap(
                url = repo?.owner?.onUser?.avatarUrl?.toString() ?: ""
            )
        )
    }

    Card(
        modifier = modifier,
        onClick = { onItemClicked(repo?.id!!) },
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f, matchHeightConstraintsFirst = true),
                    bitmap = painter,
                    contentDescription = "Profile Picture"
                )

                Spacer(modifier = Modifier.width(6.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = repo?.name ?: "Repository",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        fontSize = 18.sp,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onBackground
                    )

                    Text(
                        text = repo?.languages?.nodes?.map { it?.name }?.joinToString(", ")
                            ?: "Repository",
                        fontWeight = FontWeight.Normal,
                        maxLines = 1,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }

            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                text = repo?.description ?: "This repo does not have a description",
                fontWeight = FontWeight.Medium,
                maxLines = 4,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onBackground
            )

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp)) {
                Icon(
                    modifier = Modifier,
                    painter = if (MaterialTheme.colors.isLight) painterResource("ic_star.png")
                    else painterResource("ic_star_dark.png"),
                    contentDescription = "",
                    tint = MaterialTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    modifier = Modifier,
                    text = repo?.stargazerCount?.toString() ?: "0",
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onBackground,
                )

                Spacer(modifier = Modifier.width(12.dp))

                Icon(
                    modifier = Modifier,
                    painter = if (MaterialTheme.colors.isLight) painterResource("ic_fork.png")
                    else painterResource("ic_fork_dark.png"),
                    contentDescription = "",
                    tint = MaterialTheme.colors.onSurface
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    modifier = Modifier,
                    text = repo?.forkCount?.toString() ?: "0",
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
    }
}
