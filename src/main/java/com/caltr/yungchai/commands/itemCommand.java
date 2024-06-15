package com.caltr.yungchai.commands;

import com.caltr.yungchai.item.common.commons;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class itemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            ItemStack toSend = new ItemStack(Material.AIR);

            switch (strings[0]) {
                case "swordman_sword": {
                    toSend = commons.SWORDSMAN_SWORD();
                    break;
                }
                case "swordman_shield": {
                    toSend = commons.SWORDSMAN_SHIELD();
                    break;
                }
            }
            player.getInventory().addItem(toSend);

            return true;
        } else {
            return false;
        }
    }
}