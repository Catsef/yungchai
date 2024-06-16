package com.caltr.yungchai.item.uncommon;

import com.caltr.yungchai.item.GearStar;
import com.caltr.yungchai.util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class UncommonGearStars extends GearStar {

    public net.md_5.bungee.api.ChatColor lime = util.asCol("#91ebba");

    @Override
    public ItemStack gearItem(int n) {
        ItemStack b = new ItemStack(Material.LIGHT_GRAY_WOOL, n);
        ItemMeta m = b.getItemMeta();
        m.setDisplayName(ChatColor.BOLD + "" + util.asCol("#91ebba") + "Uncommon Gear Star");
        List<String> lore = new ArrayList();
        lore.add(ChatColor.BOLD + "" + lime + "l" + ChatColor.RESET + " BRAWLER");
        lore.add(ChatColor.BOLD + "" + lime + "l" + ChatColor.RESET + " HUNTER");
        lore.add(ChatColor.BOLD + "" + lime + "l" + ChatColor.RESET + " SWORDSMAN");
        b.setItemMeta(m);
        return b;
    }

    @Override
    public ItemStack gearUnwrap() {
        return null;
    }
    // healer and gladiator

}
