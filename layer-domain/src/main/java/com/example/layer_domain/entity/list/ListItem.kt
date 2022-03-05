package com.example.layer_domain.entity.list

/**
 * All classes that represent list items should implement this interface
 * */
interface ListItem<T> {
    /**
     * Use it if you have multiple view types
     * */
    fun getViewType(): Int = DEFAULT_ITEM_LAYOUT_KEY
    fun areContentsTheSame(other: T) = compareAnnotatedFields(other, Content::class.java)
    fun areItemsTheSame(other: T) = compareAnnotatedFields(other, ListItemId::class.java)

    companion object {
        const val DEFAULT_ITEM_LAYOUT_KEY = 0
    }
}