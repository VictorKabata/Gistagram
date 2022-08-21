package com.vickikbt.gistagram.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.apollographql.apollo3.api.json.BufferedSinkJsonWriter.Companion.string
import java.util.*

@Composable
fun DialogPreferenceSelection(
    showDialog: Boolean,
    title: String,
    currentValue: String? = null,
    labels: Array<String>,
    onNegativeClick: () -> Unit,
    onOptionSelected: (Int) -> Unit
) {

    if (showDialog) {
        Dialog(onDismissRequest = { onNegativeClick() }) {
            Card(
                elevation = 8.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 22.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        labels.forEachIndexed { index, option ->

                            ItemPreferenceOption(
                                optionText = option,
                                selectedOption = option == currentValue
                            ) {
                                onOptionSelected(index)
                                onNegativeClick()
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            modifier = Modifier.clickable { onNegativeClick() },
                            text = stringResource(id = com.vickikbt.gistagram.R.string.cancel).uppercase(Locale.getDefault()),
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
