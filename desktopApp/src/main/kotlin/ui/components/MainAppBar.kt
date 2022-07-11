package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String = "Gistagram",
    onSearch: (String) -> Unit,
    onSettingsClicked: () -> Unit,
    navigationBar: @Composable() () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = title,
                style = MaterialTheme.typography.h5,
                // fontFamily = "Boomarang", ToDo
                fontSize = 20.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
                maxLines = 1,
                color = MaterialTheme.colors.onSurface
            )

            TextField(
                modifier = Modifier,
                value = searchQuery,
                singleLine = true,
                onValueChange = { searchQuery = it },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.onSurface,
                    backgroundColor = MaterialTheme.colors.surface.copy(alpha = .6f)
                ),
                placeholder = {
                    Text(
                        text = "Search",
                        style = MaterialTheme.typography.h4,
                        fontSize = 16.sp,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Start,
                        maxLines = 1,
                        color = MaterialTheme.colors.onSurface.copy(alpha = .6f)
                    )
                },
                maxLines = 1,
                trailingIcon = {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
                },
                shape = RoundedCornerShape(6.dp)
            )

            navigationBar.invoke()
        }

        Divider(thickness = 1.dp, color = Color.Gray)
    }
}
