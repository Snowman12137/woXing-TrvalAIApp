package com.example.kamteamapp.ui.item.LazyPart

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.kamteamapp.ui.item.ListItem

@Composable
fun rememberItemProvider(customLazyListScope: CustomLazyListScope.() -> Unit): ItemProvider {
    val customLazyListScopeState = remember { mutableStateOf(customLazyListScope) }.apply {
        value = customLazyListScope
    }

    return remember {
        ItemProvider(
            derivedStateOf {
                val layoutScope = CustomLazyListScopeImpl().apply(customLazyListScopeState.value)
                layoutScope.items
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
class ItemProvider(
    private val itemsState: State<List<LazyLayoutItemContent>>
) : LazyLayoutItemProvider {

    override val itemCount
        get() = itemsState.value.size

    @Composable
    override fun Item(index: Int, key: Any) {
        val item = itemsState.value.getOrNull(index)
        item?.itemContent?.invoke(item.item)
    }

    fun refresh_y(){

    }

    fun getItemIndexesInRange(boundaries: ViewBoundaries): List<Int> {
        val result = mutableListOf<Int>()
        //Log.d(LOG_TAG, "Starting to process list with size: ${itemsState.value.size}")
        itemsState.value.forEachIndexed { index, itemContent ->


            val listItem = itemContent.item
            if (listItem.x in boundaries.fromX..boundaries.toX &&
                listItem.y in boundaries.fromY..boundaries.toY
            ) {
                result.add(index)
            }
            //Log.d(LOG_TAG, "Processing item at index $index: x = ${listItem.x}, y = ${listItem.y},long = ${listItem.long}")
        }

        return result
    }

    fun getItem(index: Int): ListItem? {
        return itemsState.value.getOrNull(index)?.item
    }
}
