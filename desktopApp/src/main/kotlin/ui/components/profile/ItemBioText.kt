package ui.components.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemBioText(
    modifier: Modifier = Modifier,
    textColor: Color? = null,
    text: String
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(if (MaterialTheme.colors.isLight) "ic_logo.png" else "ic_logo_dark.png"),
            contentDescription = text,
            tint = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            color = textColor ?: MaterialTheme.colors.onSurface,
            fontSize = 14.sp,
            letterSpacing = 0.5.sp,
        )
    }
}
