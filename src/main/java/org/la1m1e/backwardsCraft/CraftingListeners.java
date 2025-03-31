package org.la1m1e.backwardsCraft;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CraftingListeners implements Listener {
    @EventHandler
    public void onPlayerCraft(CraftItemEvent event) {
        int num = 0;
        for (ItemStack item : event.getInventory().getMatrix()) {
            if (item != null && item.getAmount() > 0) {
                num += item.getAmount();
            }
        }
        if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.ENDER_PEARL){
            event.getWhoClicked().getInventory().addItem(new ItemStack(Material.BLAZE_POWDER, num));
        }
        if (Objects.requireNonNull(event.getCurrentItem()).getType() == Material.WHITE_WOOL){
            event.getWhoClicked().getInventory().addItem(new ItemStack(Material.OAK_PLANKS,3));
        }

    }

}
