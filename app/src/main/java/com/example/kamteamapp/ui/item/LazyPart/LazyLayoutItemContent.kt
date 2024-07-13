package com.example.kamteamapp.ui.item.LazyPart

import androidx.compose.runtime.Composable
import com.example.kamteamapp.ui.item.ListItem

typealias ComposableItemContent = @Composable (ListItem) -> Unit

data class LazyLayoutItemContent(
    val item: ListItem,
    val itemContent: ComposableItemContent
)