package com.caltr.yungchai;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Yungchai extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);


    }

    @Override
    public void onDisable () {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {

    }

    @EventHandler
    public void onDrop (PlayerDropItemEvent event) {

    }

    @EventHandler
    public void onInteract (PlayerInteractEvent event) {

    }
}
