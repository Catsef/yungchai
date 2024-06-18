package com.caltr.yungchai.maps;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import java.util.HashMap;
import java.util.UUID;

public class HeaverTracker {

    public HashMap<UUID, Player> Heavers;

    public void setup () {
        Heavers = new HashMap<>();
    }

    public void push (Snowball snowball, Player player) {
        Heavers.put(snowball.getUniqueId(), player);
    }

    public boolean check (Snowball snowball) {
        return Heavers.containsKey(snowball.getUniqueId());
    }

    public void remove (Snowball snowball) {
        Heavers.remove(snowball.getUniqueId());
    }

}
