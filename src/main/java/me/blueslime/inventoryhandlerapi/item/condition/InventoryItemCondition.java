package me.blueslime.inventoryhandlerapi.item.condition;

import me.blueslime.inventoryhandlerapi.inventory.player.PlayerItem;
import me.blueslime.utilitiesapi.utils.consumer.PluginConsumer;

public class InventoryItemCondition {
    private PluginConsumer.ReturnablePluginConsumer<Boolean, PlayerItem> returnableConsumer;

    public InventoryItemCondition(PluginConsumer.ReturnablePluginConsumer<Boolean, PlayerItem> returnableConsumer) {
        this.returnableConsumer = returnableConsumer;
    }

    public void setClickEvent(PluginConsumer.ReturnablePluginConsumer<Boolean, PlayerItem> returnableConsumer) {
        this.returnableConsumer = returnableConsumer;
    }

    public PluginConsumer.ReturnablePluginConsumer<Boolean, PlayerItem> getCondition() {
        return returnableConsumer;
    }
}
