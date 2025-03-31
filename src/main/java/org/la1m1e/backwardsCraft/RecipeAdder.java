package org.la1m1e.backwardsCraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class RecipeAdder {
    public static void RegisterCrafts(BackwardsCraft plugin) {
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "eye_to_enderpearl"), new ItemStack(Material.ENDER_PEARL)).addIngredient(Material.ENDER_EYE));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "blazePow_to_rod"), new ItemStack(Material.BLAZE_ROD)).addIngredient(ItemStack.of(Material.BLAZE_POWDER, 2)));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "blazeEgg"), new ItemStack(Material.BLAZE_SPAWN_EGG,3)).addIngredient(ItemStack.of(Material.BLAZE_ROD, 3)));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "UncraftBed"), new ItemStack(Material.WHITE_WOOL,3)).addIngredient(ItemStack.of(Material.WHITE_BED)));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "UncraftWool"), new ItemStack(Material.STRING,1)).addIngredient(ItemStack.of(Material.WHITE_WOOL)));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "UncraftPlanks"), new ItemStack(Material.OAK_LOG,1)).addIngredient(ItemStack.of(Material.OAK_PLANKS,4)));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "UncraftFlintSt"), new ItemStack(Material.IRON_INGOT,1)).addIngredient(ItemStack.of(Material.FLINT_AND_STEEL,1)));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "UncraftPickaxe"), new ItemStack(Material.DIAMOND,3)).addIngredient(ItemStack.of(Material.DIAMOND_PICKAXE)));
        Bukkit.addRecipe(new ShapelessRecipe(new NamespacedKey(plugin, "UncraftPickaxe"), new ItemStack(Material.IRON_INGOT,3)).addIngredient(ItemStack.of(Material.IRON_AXE)));
    }
}
