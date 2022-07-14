package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String = "Gistagram",
    onSearch: (String) -> Unit,
    navigationBar: @Composable() () -> Unit,
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = modifier.weight(5f),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    // fontFamily = "Boomarang", ToDo
                    fontSize = 22.sp,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    color = MaterialTheme.colors.onSurface
                )

                TextField(
                    modifier = Modifier.wrapContentHeight(),
                    value = searchQuery,
                    singleLine = true,
                    onValueChange = { searchQuery = it },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        cursorColor = MaterialTheme.colors.onSurface,
                        backgroundColor = MaterialTheme.colors.primaryVariant,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = {
                        Text(
                            text = "Search",
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Start,
                            maxLines = 1,
                            color = MaterialTheme.colors.surface.copy(alpha = .6f)
                        )
                    },
                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    maxLines = 1,
                    leadingIcon = {
                        if (searchQuery.isEmpty()) {
                            Icon(
                                painter = if (MaterialTheme.colors.isLight) painterResource("ic_search.png")
                                else painterResource("ic_search_dark.png"),
                                contentDescription = "Search"
                            )
                        }
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    painter = if (MaterialTheme.colors.isLight) painterResource("ic_cancel.png")
                                    else painterResource("ic_cancel_dark.png"),
                                    contentDescription = "Close"
                                )
                            }
                        }
                    },
                    shape = RoundedCornerShape(18.dp)
                )

                navigationBar.invoke()
                //}

                // Divider(thickness = 1.dp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.weight(1f))
        }

        Divider(thickness = .5.dp, color = MaterialTheme.colors.primaryVariant)
    }
}
