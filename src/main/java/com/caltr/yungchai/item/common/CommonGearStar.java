package com.caltr.yungchai.item.common;

import com.caltr.yungchai.item.GearStar;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CommonGearStar extends GearStar {

    @Override
    public ItemStack gearItem(int n) {
        ItemStack b = new ItemStack(Material.LIGHT_GRAY_WOOL, n);
        ItemMeta m = b.getItemMeta();
        m.setDisplayName(ChatColor.BOLD + "" + ChatColor.GRAY + "Common Gear Star");
        List<String> lore = new ArrayList();
        lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "l" + ChatColor.RESET + " BRAWLER");
        lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "l" + ChatColor.RESET + " HUNTER");
        lore.add(ChatColor.BOLD + "" + ChatColor.GRAY + "l" + ChatColor.RESET + " SWORDSMAN");
        b.setItemMeta(m);
        return b;
    }

    @Override
    public ItemStack gearUnwrap() {
        return null;
    }
}
