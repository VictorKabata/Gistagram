package ui.components.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import ui.components.ItemCircleRepo

@Composable
fun ItemPinnedRepo(
    modifier: Modifier = Modifier,
    image: Painter = painterResource(if (MaterialTheme.colors.isLight) "ic_logo.png" else "ic_logo_dark.png"),
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
            image = image,
            contentDescription = "Pinned repositories"
            //borderColor = Color(parseColor(pinnedRepo?.languages?.nodes?.get(0)?.color))
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            modifier = Modifier.width(70.dp),
            text = pinnedRepo?.name ?: "Pinned repository",
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colors.onBackground
        )
    }
}
