package com.caltr.yungchai.item.common;

import com.caltr.yungchai.Yungchai;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class commons implements Listener {
    private final Plugin plugin;

    public commons(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {

    }

    @EventHandler
    public void onDrop (PlayerDropItemEvent event) {

    }

    @EventHandler
    public void onInteract (PlayerInteractEvent event) {
        if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) | (event.getAction() == Action.RIGHT_CLICK_AIR)) {
            ItemStack inHand = event.getPlayer().getInventory().getItemInMainHand();
            Player p = event.getPlayer();
            if (inHand.isSimilar(SWORDSMAN_SWORD())) {
                if (!Yungchai.SWORD.check(p)) {return;}
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
                Yungchai.SWORD.set(p, 20);
            } else if (inHand.isSimilar(SWORDSMAN_SHIELD())) {
                if (!Yungchai.SHIELD.check(p)) {return;}
                Vector v = p.getEyeLocation().getDirection();
                p.setVelocity(v.multiply(3));
                Yungchai.SWORD.set(p, 10);
            }
        }
    }

    public static ItemStack SWORDSMAN_SWORD() {
        ItemStack a = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Heavyblade");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity: " + ChatColor.GRAY + "Common");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "RMB to Pump Adrenaline");
        b.setUnbreakable(true);
        b.setLore(lore);
        a.setItemMeta(b);
        return a;
    }

    public static ItemStack SWORDSMAN_SHIELD() {
        ItemStack a = new ItemStack(Material.SHIELD, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Pavise Shield");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity: " + ChatColor.GRAY + "Common");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "RMB to Charge");
        b.setLore(lore);
        b.setUnbreakable(true);
        a.setItemMeta(b);
        return a;
    }




}
