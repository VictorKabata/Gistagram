package ui.screens.profile.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import ui.components.profile.ItemProfileRepo

@ExperimentalMaterialApi
@Composable
fun RepositoriesTab(
    modifier: Modifier = Modifier,
    repos: List<LoggedInUserProfileQuery.Node3?>?
) {
    Row(modifier = modifier.fillMaxSize().padding(vertical = 3.dp)) {
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.weight(4f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repos?.forEach { repo ->
                ItemProfileRepo(
                    repo = repo,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    // ToDo: On Item Clicked
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}
