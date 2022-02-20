package com.vickikbt.gistagram.ui.screens.profile.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vickikbt.gistagram.LoggedInUserProfileQuery
import com.vickikbt.gistagram.ui.components.profile.ItemProfileRepo

@ExperimentalMaterialApi
@Composable
fun RepositoriesTab(
    modifier: Modifier = Modifier,
    repos: List<LoggedInUserProfileQuery.Node3?>?
) {
    Column(modifier = modifier.fillMaxWidth()) {
        repos?.forEach { repo ->
            ItemProfileRepo(repo = repo) {
                // ToDo: On Item Clicked
            }
        }
    }
}
