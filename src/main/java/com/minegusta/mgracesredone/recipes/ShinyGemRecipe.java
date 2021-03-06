package com.minegusta.mgracesredone.recipes;

import com.google.common.collect.Lists;
import com.minegusta.mgracesredone.util.MGItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ShinyGemRecipe implements IRecipe {
    @Override
    public MGItem[] getIngriedients() {
        return new MGItem[]{new MGItem(Material.NETHER_STAR, 1), new MGItem(Material.GOLD_BLOCK, 4), new MGItem(Material.GOLD_INGOT, 4)};
    }

    @Override
    public String getName() {
        return "Shiny Gem";
    }

    @Override
    public ItemStack getResult() {
        return new ItemStack(Material.NETHER_STAR, 1)
        {
            {
                List<String> lore = Lists.newArrayList();
                lore.add(ChatColor.WHITE + "The Arkenstone... Heart of the mountain...");
                ItemMeta meta = getItemMeta();
                meta.setDisplayName(ChatColor.YELLOW + getName());
                meta.setLore(lore);
                setItemMeta(meta);
            }
        };
    }
}
