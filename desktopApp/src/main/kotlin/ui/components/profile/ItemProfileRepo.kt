package ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            loadImageBitmap(url = repo?.owner?.onUser?.avatarUrl?.toString() ?: "")
        )
    }

    Card(
        modifier = modifier,
        onClick = { onItemClicked(repo?.id!!) },
        shape = RoundedCornerShape(4.dp),
        elevation = 16.dp
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .weight(.2f),
                    bitmap = painter,
                    contentDescription = "Profile Picture"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.8f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = repo?.name ?: "Repository",
                        style = MaterialTheme.typography.h4,
                        maxLines = 1,
                        fontSize = 14.sp,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colors.onBackground
                    )

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        repo?.languages?.nodes?.forEach {
                            Text(
                                text = it?.name ?: "Repository",
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
                text = repo?.description ?: "No description",
                style = MaterialTheme.typography.body1,
                maxLines = 4,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}