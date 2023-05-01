package me.blueslime.inventoryhandlerapi.item;

import me.blueslime.inventoryhandlerapi.item.action.InventoryItemAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface InventoryItem {

    /**
     * Action of the item when you clicked it
     * @return Item Action
     */
    InventoryItemAction getAction();

    /**
     * This method is used in the StaticInventory
     * Gets the ItemStack of a player
     * @return the itemStack
     */
    ItemStack getItemStack();

    /**
     * This method is used in the DynamicInventory
     * Gets the ItemStack of a player
     * @param player to obtain the item stack
     * @return the itemStack
     */
    ItemStack getItemStack(Player player);

    /**
     * Identifier of the item in the inventory
     * @return item identifier
     */
    String getIdentifier();

    /**
     * Create a copy of this MenuItem
     * @return a new instance of the MenuItem
     */
    InventoryItem copy();

    /**
     * Create a copy of this InventoryItem
     * @return a new instance of the InventoryItem
     */
    boolean isBlocked();

    /**
     * Slot of the item in the inventory
     * @return int
     */
    int getSlot();
}
