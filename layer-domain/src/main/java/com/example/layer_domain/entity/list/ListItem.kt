package com.example.layer_domain.entity.list

interface ListItem<T> {
    fun getViewType(): Int = DEFAULT_ITEM_LAYOUT_KEY
    fun areContentsTheSame(other: T) = compareAnnotatedFields(other, Content::class.java)
    fun areItemsTheSame(other: T) = compareAnnotatedFields(other, ListItemId::class.java)

    companion object {
        const val DEFAULT_ITEM_LAYOUT_KEY = 0
    }
}