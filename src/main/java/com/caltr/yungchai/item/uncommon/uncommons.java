package com.caltr.yungchai.item.uncommon;

import com.caltr.yungchai.util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class uncommons implements Listener {

    public net.md_5.bungee.api.ChatColor lime = util.asCol("#91ebba");
    private final Plugin plugin;
    public uncommons (Plugin plugin) {
        this.plugin = plugin;
    }

    public static ItemStack UNCOMMON_ITEM (String name, String rmbPower, Material item, String quote, String lmbPower) {
        net.md_5.bungee.api.ChatColor lime = util.asCol("#91ebba");
        ItemStack a = new ItemStack(item, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(lime + "" + ChatColor.BOLD + name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity " + lime + "Common");
        lore.add(lime + "" + ChatColor.BOLD + "RMB to " + rmbPower);
        lore.add(lime + "" + ChatColor.BOLD + "LMB to " + lmbPower);
        lore.add(ChatColor.ITALIC + quote);
        b.setUnbreakable(true);
        b.setLore(lore);
        b.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        a.setItemMeta(b);
        return a;
    }

    public static ItemStack HEALER_POT () {
        return UNCOMMON_ITEM("Dark Magic", "Darkness", Material.POTION, "Painful.", "Psychotic Blast");
    }
    public static ItemStack HEALER_NEEDLE () {
        return UNCOMMON_ITEM("Healer's Needle", "Extra Heart", Material.BLAZE_ROD, "Sanitary.", "Heal");
    }
}
