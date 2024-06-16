package com.caltr.yungchai.item.common;

import com.caltr.yungchai.item.GearStar;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommonGearStar extends GearStar {

    private Random random = new Random();

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
    public void gearUnwrap(int n, Player player) {
        // return itemstack for gear unwrapping
        int set = random.nextInt(1, 3);
        Inventory i = player.getInventory();

        switch (set) {
            case 1: {
                i.addItem(commons.SWORDSMAN_SHIELD());
                i.addItem(commons.SWORDSMAN_SWORD());
                break;
            }
            case 2: {
                i.addItem(commons.BRAWLER_CHAIN());
                i.addItem(commons.BRAWLER_GLOVE());
                break;
            }
            case 3: {
                i.addItem(commons.HUNTER_AXE());
                i.addItem(commons.HUNTER_BOW());
                break;
            }
        }

    }
}
