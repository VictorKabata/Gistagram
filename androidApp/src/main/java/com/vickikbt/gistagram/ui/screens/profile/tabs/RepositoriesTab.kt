package com.vickikbt.gistagram.ui.screens.profile.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.gistagram.ui.components.profile.ItemProfileRepo

@ExperimentalMaterialApi
@Composable
fun RepositoriesTab(
    modifier: Modifier = Modifier,
    repos: List<LoggedInUserProfileQuery.Node3?>?
) {
    Column(
        modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repos?.forEach { repo ->
            ItemProfileRepo(
                repo = repo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                // ToDo: On Item Clicked
            }
        }
    }
}
