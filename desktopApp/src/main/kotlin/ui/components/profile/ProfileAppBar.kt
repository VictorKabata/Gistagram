package ui.components.profile

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileAppBar(modifier: Modifier = Modifier, title: String, onSettingsClicked: () -> Unit) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier,
                text = title,
                style = MaterialTheme.typography.h5,
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
                color = MaterialTheme.colors.onSurface
            )
        },
        actions = {
            IconButton(onClick = { onSettingsClicked() }) {
                Icon(
                    painter = painterResource("ic_settings.svg"),
                    contentDescription = "Settings",
                    tint = MaterialTheme.colors.onSurface
                )
            }
        },
        contentColor = MaterialTheme.colors.onSurface,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 16.dp
    )
}
