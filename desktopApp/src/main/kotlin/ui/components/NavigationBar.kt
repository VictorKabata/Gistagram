package ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.navigation.NavController
import ui.navigation.NavigationItem

@Composable
fun NavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.onSurface,
    navigationDestinations: List<NavigationItem>
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        navigationDestinations.forEach { item ->
            val isSelected = item.route == navController.currentDestination.value
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = item.icon!!,
                contentDescription = item.title,
                tint = if (isSelected) MaterialTheme.colors.onSurface
                else MaterialTheme.colors.onSurface.copy(alpha = .6f),
            )
        }
    }
}
