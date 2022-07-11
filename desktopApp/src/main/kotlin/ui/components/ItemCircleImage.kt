package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ItemCircleImage(
    modifier: Modifier = Modifier,
    image: ImageBitmap,
    contentDescription: String,
    onClick: () -> Unit
) {

    Image(
        modifier = modifier
            .clip(CircleShape)
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(width = 2.dp, color = Color.Gray, shape = CircleShape)
            .clickable { onClick() },
        bitmap = image,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}
