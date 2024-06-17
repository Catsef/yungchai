package com.caltr.yungchai;

import com.caltr.yungchai.commands.autoComplete;
import com.caltr.yungchai.commands.itemCommand;
import com.caltr.yungchai.cooldown.BasicCooldown;
import com.caltr.yungchai.item.common.commons;
import com.caltr.yungchai.item.uncommon.uncommons;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Yungchai extends JavaPlugin implements Listener {

    public JavaPlugin plugin() {
        return this;
    }

    public static BasicCooldown SWORD;
    public static BasicCooldown SHIELD;
    public static BasicCooldown AXE;
    public static BasicCooldown BOW;
    public static BasicCooldown GLOVE;
    public static BasicCooldown CHAIN;
    public static BasicCooldown POT;
    public static BasicCooldown NEEDLE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

        commons Commons = new commons(this);
        getServer().getPluginManager().registerEvents(Commons, this);

        uncommons Uncommons = new uncommons(this);
        getServer().getPluginManager().registerEvents(Uncommons, this);

        SWORD = new BasicCooldown(); SWORD.setup();
        SHIELD = new BasicCooldown(); SHIELD.setup();
        BOW = new BasicCooldown(); BOW.setup();
        AXE = new BasicCooldown(); AXE.setup();
        GLOVE = new BasicCooldown(); GLOVE.setup();
        CHAIN = new BasicCooldown(); CHAIN.setup();
        POT = new BasicCooldown(); POT.setup();

        this.getCommand("item").setExecutor(new itemCommand());
        this.getCommand("item").setTabCompleter(new autoComplete());
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
