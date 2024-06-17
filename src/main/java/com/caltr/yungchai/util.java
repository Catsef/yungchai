package com.caltr.yungchai;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class util {

    public static net.md_5.bungee.api.ChatColor asCol(String a) {
        return net.md_5.bungee.api.ChatColor.of(a);
    }
    public static void smsg(Player p, String name, int cool) {
        p.sendMessage(String.format(ChatColor.RED + name + " is on cooldown (for %d seconds)", cool));
    }
}
