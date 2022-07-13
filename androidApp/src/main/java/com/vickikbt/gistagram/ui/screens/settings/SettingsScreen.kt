package com.vickikbt.gistagram.ui.screens.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.gistagram.R
import com.vickikbt.gistagram.ui.components.DialogPreferenceSelection
import com.vickikbt.gistagram.ui.components.PreferencesGroup
import com.vickikbt.gistagram.ui.components.TextPreference
import com.vickikbt.gistagram.ui.components.app_bars.AppBar
import com.vickikbt.shared.domain.utils.Constants
import org.koin.androidx.compose.get

@Composable
fun SettingsScreen(navController: NavController, viewModel: SettingsViewModel = get()) {

    val context = LocalContext.current

    val currentTheme = viewModel.appTheme.collectAsState().value ?: 0
    val showThemeDialog = remember { mutableStateOf(false) }
    val themeLabel = stringArrayResource(id = R.array.theme_labels)[currentTheme]

    Scaffold(
        topBar = { AppBar(stringResource(id = R.string.title_settings)) }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            color = MaterialTheme.colors.surface
        ) {
            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(8.dp))

                PreferencesGroup(title = stringResource(id = R.string.title_personalisation)) {
                    TextPreference(
                        painter = painterResource(id = R.drawable.ic_theme),
                        title = stringResource(id = R.string.change_theme),
                        subTitle = themeLabel,
                        onClick = { showThemeDialog.value = !showThemeDialog.value }
                    )

                    if (showThemeDialog.value) ChangeTheme(
                        viewModel = viewModel,
                        showDialog = showThemeDialog,
                        currentValue = themeLabel
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                PreferencesGroup(
                    title = stringResource(id = R.string.title_extras),
                    isLast = true
                ) {
                    TextPreference(
                        painter = painterResource(id = R.drawable.ic_report_bug),
                        title = stringResource(id = R.string.report_bug),
                        subTitle = stringResource(id = R.string.report_bug_description),
                        onClick = { reportBug(context) }
                    )

                    TextPreference(
                        modifier = Modifier.clickable { },
                        painter = painterResource(id = R.drawable.ic_github),
                        title = stringResource(id = R.string.source_code),
                        subTitle = stringResource(id = R.string.source_code_description),
                        onClick = { openSourceCode(context) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ChangeTheme(
    viewModel: SettingsViewModel,
    showDialog: MutableState<Boolean>,
    currentValue: String?
) {
    DialogPreferenceSelection(
        showDialog = showDialog.value,
        title = stringResource(id = R.string.change_theme),
        currentValue = currentValue ?: stringResource(id = R.string.def),
        labels = stringArrayResource(id = R.array.theme_labels),
        onNegativeClick = { showDialog.value = false }
    ) { theme ->
        viewModel.setAppTheme(theme = theme)
    }
}

private fun reportBug(context: Context) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        type = "text/plain"
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(Constants.BUG_REPORT_EMAIL))
        putExtra(Intent.EXTRA_SUBJECT, Constants.BUG_REPORT_SUBJECT)
    }

    context.startActivity(emailIntent)
}

private fun openSourceCode(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(Constants.SOURCE_CODE_URL)
    context.startActivity(intent)
}
