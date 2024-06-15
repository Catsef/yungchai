package com.caltr.yungchai.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;

abstract public class Cooldown {
    public HashMap map;
    public abstract void setup();
    public abstract void set(Player player, double secs);
    public abstract int get(Player player);
    public abstract boolean check(Player player);
}
