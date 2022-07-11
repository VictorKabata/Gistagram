package ui.components.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemBioText(
    modifier: Modifier = Modifier,
    textColor: Color? = null,
    text: String,
    image: ImageVector? = null
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        image?.let {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = it,
                contentDescription = text,
                tint = MaterialTheme.colors.onSurface
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = textColor ?: MaterialTheme.colors.onSurface,
            letterSpacing = 0.5.sp,
        )
    }
}
