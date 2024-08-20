package me.blueslime.inventoryhandlerapi.inventory.types;

import me.blueslime.inventoryhandlerapi.InventoryHandlerAPI;
import me.blueslime.inventoryhandlerapi.inventory.CustomInventory;
import me.blueslime.inventoryhandlerapi.inventory.player.PlayerItem;
import me.blueslime.inventoryhandlerapi.item.InventoryItem;

import me.blueslime.inventoryhandlerapi.item.list.WrapperInventoryItem;
import me.blueslime.utilitiesapi.item.nbt.ItemNBT;
import me.blueslime.utilitiesapi.utils.consumer.PluginConsumer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class DynamicInventory extends CustomInventory {

    public DynamicInventory(String identifier) {
        super(identifier);

        InventoryHandlerAPI.getInventories().put(
                identifier,
                this
        );
    }

    private void load(Player player, boolean clearInventory) {
        if (clearInventory) {
            player.getInventory().clear();
        }

        for (InventoryItem item : getItemStorage().getValues()) {
            InventoryItem inventoryItem;

            if (item instanceof WrapperInventoryItem) {
                inventoryItem = getItemBuilder().processItem(
                        player,
                        item
                );
            } else {
                inventoryItem = getItemBuilder().processItem(
                        player,
                        item.copy()
                );
            }

            ItemStack itemStack = inventoryItem.getItemStack();

            itemStack = ItemNBT.addString(
                    itemStack, InventoryHandlerAPI.getCustomPrefix() + getId(),
                    inventoryItem.getIdentifier()
            );

            itemStack = ItemNBT.addString(
                    itemStack, InventoryHandlerAPI.getCustomIdentifierPrefix() + "name",
                    getId()
            );

            if (inventoryItem.isBlocked()) {
                itemStack = ItemNBT.addString(
                        itemStack,
                        InventoryHandlerAPI.getCustomIdentifierPrefix() + "blockedItem",
                        "true"
                );
            }

            if (inventoryItem.getCondition() != null) {
                PluginConsumer.ReturnablePluginConsumer<Boolean, PlayerItem> consumer = inventoryItem.getCondition().getCondition();
                if (consumer != null) {
                    if (!consumer.accept(PlayerItem.build(inventoryItem, player))) {
                        return;
                    }
                }
            }

            player.getInventory().setItem(
                    inventoryItem.getSlot(),
                    itemStack
            );

        }
        player.updateInventory();
    }

    @Override
    public void fillBorders() {

    }

    @Override
    public void fillRows() {

    }

    @Override
    public Collection<InventoryItem> getItemList() {
        return getItemStorage().getValues();
    }

    @Override
    public void setInventory(Player player, boolean clearInventory) {
        load(player, clearInventory);
    }
}
