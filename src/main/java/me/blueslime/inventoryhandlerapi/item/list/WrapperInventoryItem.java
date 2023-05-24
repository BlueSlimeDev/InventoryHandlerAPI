package me.blueslime.inventoryhandlerapi.item.list;

import me.blueslime.inventoryhandlerapi.item.InventoryItem;
import me.blueslime.inventoryhandlerapi.item.list.builder.DefaultInventoryItemBuilder;
import me.blueslime.inventoryhandlerapi.item.action.InventoryItemAction;
import me.blueslime.inventoryhandlerapi.item.list.builder.WrapperInventoryItemBuilder;
import me.blueslime.utilitiesapi.item.ItemWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WrapperInventoryItem implements InventoryItem {
    private final InventoryItemAction action;

    private final ItemWrapper wrapper;

    private final String identifier;

    private final boolean blocked;

    private final int slot;

    private WrapperInventoryItem(String identifier, int slot, ItemWrapper wrapper, boolean blocked, InventoryItemAction action) {
        this.identifier = identifier;
        this.wrapper = wrapper;
        this.blocked = blocked;
        this.action = action;
        this.slot = slot;

    }

    public static InventoryItem fromItem(String identifier, int slot, ItemWrapper wrapper, boolean blockedItem, InventoryItemAction action) {
        return new WrapperInventoryItem(
                identifier,
                slot,
                wrapper,
                blockedItem,
                action
        );
    }

    public static InventoryItem fromItem(String identifier, int slot, ItemWrapper wrapper, boolean blockedItem) {
        return fromItem(identifier, slot, wrapper, blockedItem, null);
    }

    public static InventoryItem fromItem(String identifier, int slot, ItemWrapper wrapper) {
        return fromItem(identifier, slot, wrapper, false, null);
    }

    public static InventoryItem fromItem(String identifier, ItemWrapper wrapper, boolean blockedItem) {
        return fromItem(identifier, -1, wrapper, blockedItem, null);
    }

    public static InventoryItem fromItem(String identifier, ItemWrapper wrapper) {
        return fromItem(identifier, -1, wrapper, false, null);
    }

    public static DefaultInventoryItemBuilder builder(String identifier, int slot) {
        return new DefaultInventoryItemBuilder(identifier, slot);
    }

    public static DefaultInventoryItemBuilder builder(String identifier) {
        return new DefaultInventoryItemBuilder(identifier, -1);
    }

    public WrapperInventoryItemBuilder asBuilder() {
        return new WrapperInventoryItemBuilder(
                this.identifier,
                this.slot
        ).action(
                this.action
        ).cancelClick(
                this.blocked
        ).item(
                this.wrapper
        );
    }

    public InventoryItemAction getAction() {
        return action;
    }

    public ItemStack getItemStack() {
        return wrapper.getItem();
    }

    @Override
    public ItemStack getItemStack(Player player) {
        return wrapper.getDynamicItem(player).getItem();
    }

    public boolean isBlocked() {
        return blocked;
    }

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public InventoryItem copy() {
        return new WrapperInventoryItem(
                this.identifier,
                this.slot,
                this.wrapper.clone(),
                this.blocked,
                this.action
        );
    }

    public int getSlot() {
        return slot;
    }

    public InventoryItem clone(String identifier, int slot) {
        return new WrapperInventoryItem(identifier, slot, this.wrapper, this.blocked, this.action);
    }

    public InventoryItem clone(String identifier, InventoryItemAction action) {
        return new WrapperInventoryItem(identifier, this.slot, this.wrapper, this.blocked, action);
    }

    public InventoryItem clone(String identifier, ItemWrapper wrapper) {
        return new WrapperInventoryItem(identifier, this.slot, wrapper, this.blocked, this.action);
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "identifier=" + identifier +
                ", slot=" + slot +
                ", wrapper=" + wrapper +
                ", blocked=" + blocked +
                '}';
    }
}

