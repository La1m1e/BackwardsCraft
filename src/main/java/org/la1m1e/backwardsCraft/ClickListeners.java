package org.la1m1e.backwardsCraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static org.la1m1e.backwardsCraft.BackwardsCraft.bossBar;

public class ClickListeners implements Listener {
    @EventHandler
    public void AddBeds(PlayerInteractEvent event){
        if (event.getClickedBlock() == null) return;
        if (Objects.requireNonNull(event.getClickedBlock()).getType() == Material.WHITE_BED) {
            event.getPlayer().getInventory().addItem(ItemStack.of(Material.WHITE_BED));
            event.getPlayer().getInventory().addItem(ItemStack.of(Material.WHITE_BED));
        }
    }
    @EventHandler
    public void UnLightNetherPortal(PlayerInteractEvent event){
        if (event.getClickedBlock() == null) return;
        if (Objects.requireNonNull(event.getClickedBlock()).getType() == Material.NETHER_PORTAL && event.getPlayer().getItemInHand().equals(ItemStack.of(Material.FLINT_AND_STEEL))) {
            Block clicked = event.getClickedBlock();
            int radius = 4;
            int x = clicked.getX();
            int y = clicked.getY();
            int z = clicked.getZ();

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dz = -radius; dz <= radius; dz++) {
                        Block block = clicked.getWorld().getBlockAt(x + dx, y + dy, z + dz);
                        if (block.getType() == Material.OBSIDIAN || block.getType() == Material.NETHER_PORTAL) {
                            if (block.getType() == Material.OBSIDIAN) {
                                event.getPlayer().getInventory().addItem(ItemStack.of(Material.OBSIDIAN));
                            }
                            block.setType(Material.AIR);
                        }
                    }
                }
            }
            event.getClickedBlock().getLocation();

        }
    }
    @EventHandler
    public void TeleportFromEnd(PlayerInteractEvent event){
        if (event.getClickedBlock() == null) return;
        if (Objects.requireNonNull(event.getClickedBlock()).getLocation().getBlockX() <= 110 && event.getClickedBlock().getLocation().getBlockX() >= 90 && event.getClickedBlock().getLocation().getBlockZ() <=5 && event.getClickedBlock().getLocation().getBlockZ() >=-5 && event.getClickedBlock().getType() == Material.OBSIDIAN){
            event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), -1201, -30, 1762));
            bossBar.removePlayer(event.getPlayer());
        }
    }
    @EventHandler
    public void TakeEnderEyesOut(PlayerInteractEvent event){
        if (event.getClickedBlock() == null) return;
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clickedBlock = event.getClickedBlock();
            assert clickedBlock != null;
            if (clickedBlock.getType() == Material.END_PORTAL_FRAME) {
                EndPortalFrame frameData = (EndPortalFrame) clickedBlock.getBlockData();
                if (frameData.hasEye()) {
                    frameData.setEye(false);
                    clickedBlock.setBlockData(frameData);
                    event.getPlayer().getInventory().addItem(ItemStack.of(Material.ENDER_EYE));
                    int radius = 4;
                    int x = clickedBlock.getX();
                    int y = clickedBlock.getY();
                    int z = clickedBlock.getZ();
                    for (int dx = -radius; dx <= radius; dx++) {
                        for (int dy = -radius; dy <= radius; dy++) {
                            for (int dz = -radius; dz <= radius; dz++) {
                                Block block = clickedBlock.getWorld().getBlockAt(x + dx, y + dy, z + dz);
                                if (block.getType() == Material.END_PORTAL) {
                                    block.setType(Material.AIR);  // Replace END_PORTAL with AIR
                                }
                            }
                        }
                    }
                }
            }

        }
    }
    @EventHandler
    public void BucketObsidian(PlayerInteractEvent event){
        if (event.getClickedBlock() == null) return;
        if (Objects.requireNonNull(event.getClickedBlock()).getType() == Material.OBSIDIAN && event.getPlayer().getItemInHand().equals(ItemStack.of(Material.BUCKET))){
            event.getClickedBlock().setType(Material.AIR);
            event.getPlayer().getItemInHand().setType(Material.LAVA_BUCKET);
        }

    }
}
