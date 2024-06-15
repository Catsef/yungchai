package com.caltr.yungchai.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BasicCooldown extends Cooldown {
    public static HashMap<UUID, Double> map;

    @Override
    public void setup() {
        map = new HashMap<>();
    }

    @Override
    public void set(Player player, double secs) {
        double newTime = System.currentTimeMillis() + (secs*1000);
        map.put(player.getUniqueId(), newTime);
    }

    @Override
    public int get(Player player) {
        return Math.toIntExact(Math.round((map.get(player.getUniqueId()) - System.currentTimeMillis())/1000));
    }

    // if you can do it again. False for no true for yes.
    @Override
    public boolean check(Player player) {
        return !map.containsKey(player.getUniqueId()) || map.get(player.getUniqueId()) <= System.currentTimeMillis();
    }
}
