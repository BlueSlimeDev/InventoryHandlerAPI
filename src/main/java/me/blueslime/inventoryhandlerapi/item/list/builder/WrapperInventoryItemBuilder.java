package me.blueslime.inventoryhandlerapi.item.list.builder;

import me.blueslime.inventoryhandlerapi.item.InventoryItem;
import me.blueslime.inventoryhandlerapi.item.action.InventoryItemAction;
import me.blueslime.inventoryhandlerapi.item.list.WrapperInventoryItem;
import me.blueslime.utilitiesapi.item.ItemWrapper;

public class WrapperInventoryItemBuilder {
    private InventoryItemAction action = null;

    private ItemWrapper wrapper = null;

    private boolean blocked = false;

    private final String identifier;

    private int slot;

    public WrapperInventoryItemBuilder(String identifier, int slot) {
        this.identifier = identifier;
        this.slot = slot;
    }

    public WrapperInventoryItemBuilder slot(int slot) {
        this.slot = slot;
        return this;
    }

    public WrapperInventoryItemBuilder action(InventoryItemAction action) {
        this.action = action;
        return this;
    }

    public WrapperInventoryItemBuilder item(ItemWrapper wrapper) {
        this.wrapper = wrapper;
        return this;
    }

    public WrapperInventoryItemBuilder cancelClick(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public InventoryItem build() {
        return WrapperInventoryItem.fromItem(
                identifier,
                slot,
                wrapper,
                blocked,
                action
        );
    }
}

