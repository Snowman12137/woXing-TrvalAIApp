package com.example.kamteamapp.ui.item.LazyPart

import com.example.kamteamapp.ui.item.ListItem


interface CustomLazyListScope {

    fun items(items: List<ListItem>, itemContent: ComposableItemContent)
}




class CustomLazyListScopeImpl : CustomLazyListScope {

    private val _items = mutableListOf<LazyLayoutItemContent>()
    val items: List<LazyLayoutItemContent> = _items

    override fun items(items: List<ListItem>, itemContent: ComposableItemContent) {
        items.forEach { _items.add(LazyLayoutItemContent(it, itemContent)) }
    }
}
