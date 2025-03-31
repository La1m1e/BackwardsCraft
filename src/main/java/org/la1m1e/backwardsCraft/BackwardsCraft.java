package org.la1m1e.backwardsCraft;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class BackwardsCraft extends JavaPlugin implements Listener {
    public static BossBar bossBar;
    boolean FirstDragon = true;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new CraftingListeners(), this);
        Bukkit.getPluginManager().registerEvents(new PiglinReverter(this), this);
        Bukkit.getPluginManager().registerEvents(new ClickListeners(), this);
        RecipeAdder.RegisterCrafts(this);
            }

    @Override
    public void onDisable() {
        getLogger().info("ClickBlockPlugin has been disabled!");
    }

    @EventHandler
    public void onExplosion(BlockExplodeEvent event) {
        if (FirstDragon) {
            FirstDragon = false;
            EnderDragon dragon = (EnderDragon) event.getBlock().getWorld().spawnEntity(event.getBlock().getLocation().add(0, 3, 0), EntityType.ENDER_DRAGON);
            dragon.setHealth(1.0);
            bossBar = Bukkit.createBossBar("Ender Dragon", BarColor.PURPLE, BarStyle.SOLID);
            bossBar.setProgress(dragon.getHealth() / dragon.getMaxHealth()); // Sync with dragon's health
            for (Player player : Bukkit.getOnlinePlayers()) {
                bossBar.addPlayer(player);
            }
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                if (dragon.isDead()) {
                    bossBar.setVisible(false);
                    bossBar.removeAll();
                } else {
                    bossBar.setProgress(Math.max(0, dragon.getHealth() / dragon.getMaxHealth())); // Prevent negative values
                }
            }, 0L, 10L);
        }

        for (Entity entity : event.getBlock().getWorld().getEntities()) {
            if (entity instanceof EnderDragon dragon) {
                dragon.setHealth(Math.min(dragon.getHealth() + 55.0, dragon.getMaxHealth()));
            }
        }
    }
    @EventHandler
    public void onDragonDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof EnderDragon) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onEat(PlayerItemConsumeEvent event){
        event.getPlayer().getInventory().addItem(ItemStack.of(event.getPlayer().getActiveItem().getType()));
    }
}
