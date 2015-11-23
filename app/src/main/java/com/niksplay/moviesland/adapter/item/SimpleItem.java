package com.niksplay.moviesland.adapter.item;

/**
 * Created by nikita on 21.11.15.
 */
public abstract class SimpleItem<T> implements IListItem {

    private T itemData;

    private int itemType;

    public SimpleItem(T data, int type) {
        this.itemData = data;
        this.itemType = type;
    }

    public T getItemData() {
        return itemData;
    }

    @Override
    public int getType() {
        return itemType;
    }
}
