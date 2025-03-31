package org.la1m1e.backwardsCraft;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.EnumSet;
import java.util.Set;

public class PiglinReverter implements Listener {

    private final BackwardsCraft plugin;

    public PiglinReverter(BackwardsCraft plugin) {
        this.plugin = plugin;  // Store the plugin instance
    }

    private static final Set<Material> BARTER_ITEMS = EnumSet.of(
            Material.ENDER_PEARL, Material.OBSIDIAN, Material.GRAVEL,
            Material.BLACKSTONE, Material.FIRE_CHARGE, Material.LEATHER,
            Material.NETHER_BRICK, Material.SPECTRAL_ARROW, Material.STRING,
            Material.SOUL_SAND, Material.POTION, Material.IRON_NUGGET,
            Material.QUARTZ, Material.CRYING_OBSIDIAN, Material.MAGMA_CREAM
    );

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        Material itemType = droppedItem.getType();

        if (BARTER_ITEMS.contains(itemType)) {
            int droppedItemCount = droppedItem.getAmount();

            Piglin piglin = player.getWorld().getNearbyEntities(player.getLocation(), 5, 5, 5).stream()
                    .filter(entity -> entity instanceof Piglin)
                    .map(entity -> (Piglin) entity)
                    .findFirst()
                    .orElse(null);

            if (piglin != null) {
                Item droppedItemEntity = event.getItemDrop();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        droppedItemEntity.remove();
                        for (int i = 0; i < droppedItemCount; i++){
                            Item item = piglin.getWorld().dropItem(piglin.getLocation().add(0,1,0), new ItemStack(Material.GOLD_INGOT));
                            item.setVelocity(player.getLocation().toVector().subtract(piglin.getLocation().toVector()).normalize().multiply(0.5));
                        }
                    }
                }.runTaskLater(plugin, 60L);
            }
        }
    }

}
