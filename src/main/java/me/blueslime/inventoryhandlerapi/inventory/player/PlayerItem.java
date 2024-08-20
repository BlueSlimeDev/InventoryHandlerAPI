package me.blueslime.inventoryhandlerapi.inventory.player;

import me.blueslime.inventoryhandlerapi.item.InventoryItem;
import me.blueslime.utilitiesapi.item.ItemWrapper;
import me.blueslime.utilitiesapi.item.dynamic.DynamicItem;
import org.bukkit.entity.Player;

public class PlayerItem {
    private final InventoryItem inventoryItem;
    private final Player player;

    private PlayerItem(InventoryItem inventoryItem, Player player) {
        this.inventoryItem = inventoryItem;
        this.player = player;
    }


    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public Player getPlayer() {
        return player;
    }

    public static PlayerItem build(InventoryItem inventoryItem, Player player) {
        return new PlayerItem(inventoryItem, player);
    }

}
