package com.vickikbt.gistagram.ui.screens.profile.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.gistagram.ui.components.profile.ItemProfileRepo

@ExperimentalMaterialApi
@Composable
fun RepositoriesTab(
    modifier: Modifier = Modifier,
    repos: List<LoggedInUserProfileQuery.Node3?>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repos.forEach { repo ->
            ItemProfileRepo(
                repo = repo,
                modifier = Modifier.fillMaxWidth(0.95f)
            ) {
                // ToDo: On Item Clicked
            }
        }
    }
}
