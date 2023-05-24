package me.blueslime.inventoryhandlerapi.item.list;

import me.blueslime.inventoryhandlerapi.item.InventoryItem;
import me.blueslime.inventoryhandlerapi.item.list.builder.DefaultInventoryItemBuilder;
import me.blueslime.inventoryhandlerapi.item.action.InventoryItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DefaultInventoryItem implements InventoryItem {
    private final InventoryItemAction action;

    private final ItemStack itemStack;

    private final String identifier;

    private final boolean blocked;

    private final int slot;

    private DefaultInventoryItem(String identifier, int slot, ItemStack itemStack, boolean blocked, InventoryItemAction action) {
        this.identifier = identifier;
        this.itemStack = itemStack;
        this.blocked = blocked;
        this.action = action;
        this.slot = slot;

    }

    public static InventoryItem fromItem(String identifier, int slot, ItemStack itemStack, boolean blockedItem, InventoryItemAction action) {
        return new DefaultInventoryItem(
                identifier,
                slot,
                itemStack,
                blockedItem,
                action
        );
    }

    public static InventoryItem fromItem(String identifier, int slot, ItemStack itemStack, boolean blockedItem) {
        return fromItem(identifier, slot, itemStack, blockedItem, null);
    }

    public static InventoryItem fromItem(String identifier, int slot, ItemStack itemStack) {
        return fromItem(identifier, slot, itemStack, false, null);
    }

    public static InventoryItem fromItem(String identifier, ItemStack itemStack, boolean blockedItem) {
        return fromItem(identifier, -1, itemStack, blockedItem, null);
    }

    public static InventoryItem fromItem(String identifier, ItemStack itemStack) {
        return fromItem(identifier, -1, itemStack, false, null);
    }

    public static DefaultInventoryItemBuilder builder(String identifier, int slot) {
        return new DefaultInventoryItemBuilder(identifier, slot);
    }

    public static DefaultInventoryItemBuilder builder(String identifier) {
        return new DefaultInventoryItemBuilder(identifier, -1);
    }

    public DefaultInventoryItemBuilder asBuilder() {
        return new DefaultInventoryItemBuilder(
                this.identifier,
                this.slot
        ).action(
                this.action
        ).cancelClick(
                this.blocked
        ).item(
                this.itemStack
        );
    }

    public InventoryItemAction getAction() {
        return action;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public ItemStack getItemStack(Player player) {
        return itemStack;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public InventoryItem copy() {
        return new DefaultInventoryItem(
                this.identifier,
                this.slot,
                itemStack.clone(),
                this.blocked,
                this.action
        );
    }

    public int getSlot() {
        return slot;
    }

    public InventoryItem clone(String identifier, int slot) {
        return new DefaultInventoryItem(identifier, slot, this.itemStack, this.blocked, this.action);
    }

    public InventoryItem clone(String identifier, InventoryItemAction action) {
        return new DefaultInventoryItem(identifier, this.slot, this.itemStack, this.blocked, action);
    }

    public InventoryItem clone(String identifier, ItemStack itemStack) {
        return new DefaultInventoryItem(identifier, this.slot, itemStack, this.blocked, this.action);
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "identifier=" + identifier +
                ", slot=" + slot +
                ", itemStack=" + itemStack +
                ", blocked=" + blocked +
                '}';
    }
}

