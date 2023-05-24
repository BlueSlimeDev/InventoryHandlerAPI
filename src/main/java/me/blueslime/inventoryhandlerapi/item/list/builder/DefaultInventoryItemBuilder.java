package me.blueslime.inventoryhandlerapi.item.list.builder;

import me.blueslime.inventoryhandlerapi.item.InventoryItem;
import me.blueslime.inventoryhandlerapi.item.action.InventoryItemAction;
import me.blueslime.inventoryhandlerapi.item.list.DefaultInventoryItem;
import org.bukkit.inventory.ItemStack;

public class DefaultInventoryItemBuilder {
    private InventoryItemAction action = null;

    private ItemStack itemStack = null;

    private boolean blocked = false;

    private final String identifier;

    private int slot;

    public DefaultInventoryItemBuilder(String identifier, int slot) {
        this.identifier = identifier;
        this.slot = slot;
    }

    public DefaultInventoryItemBuilder slot(int slot) {
        this.slot = slot;
        return this;
    }

    public DefaultInventoryItemBuilder action(InventoryItemAction action) {
        this.action = action;
        return this;
    }

    public DefaultInventoryItemBuilder item(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    public DefaultInventoryItemBuilder cancelClick(boolean blocked) {
        this.blocked = blocked;
        return this;
    }

    public InventoryItem build() {
        return DefaultInventoryItem.fromItem(
                identifier,
                slot,
                itemStack,
                blocked,
                action
        );
    }
}
