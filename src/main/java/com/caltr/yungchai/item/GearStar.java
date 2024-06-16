package com.caltr.yungchai.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

abstract public class GearStar {
    public abstract ItemStack gearItem(int n);
    public abstract void gearUnwrap(int n, Player player);

}
