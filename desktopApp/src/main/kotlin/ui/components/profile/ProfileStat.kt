package ui.components.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.gistagram.LoggedInUserProfileQuery

@Composable
fun ProfileStats(modifier: Modifier = Modifier, user: LoggedInUserProfileQuery.Viewer?) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ItemProfileStat(
            statValue = user?.repositories?.totalCount ?: 0,
            statTitle = "repos",
            onItemClicked = {}
        )

        ItemProfileStat(
            statValue = user?.followers?.totalCount ?: 0,
            statTitle = "followers",
            onItemClicked = {}
        )

        ItemProfileStat(
            statValue = user?.following?.totalCount ?: 0,
            statTitle = "following",
            onItemClicked = {}
        )
    }
}

@Composable
fun ItemProfileStat(
    modifier: Modifier = Modifier,
    statValue: Int,
    statTitle: String,
    onItemClicked: () -> Unit?
) {

    Row(
        modifier = modifier.clickable { onItemClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Text(
            text = statValue.toString(),
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = modifier.width(3.dp))

        Text(
            text = statTitle,
            fontWeight = FontWeight.Normal,
            maxLines = 1,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )
    }
}
