package com.caltr.yungchai.item.common;

import com.caltr.yungchai.Yungchai;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
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

                if (!Yungchai.SWORD.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Swordsman's Adrenaline is on cooldown (for %d seconds)", Yungchai.SWORD.get(p)));return;}

                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));

                Yungchai.SWORD.set(p, 20);
                p.setCooldown(Material.STONE_SWORD, 20*20);

            } else if (inHand.isSimilar(SWORDSMAN_SHIELD())) {

                if (!Yungchai.SHIELD.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Swordsman's Charge is on cooldown (for %d seconds)", Yungchai.SHIELD.get(p)));return;}

                Vector v = p.getEyeLocation().getDirection();
                p.setVelocity(v.multiply(3));

                Yungchai.SHIELD.set(p, 10);
                p.setCooldown(Material.SHIELD, 10*20);

            } else if (inHand.isSimilar(HUNTER_BOW())) {

                if (!Yungchai.BOW.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Hunter's Piercing Shot is on cooldown (for %d seconds)", Yungchai.BOW.get(p)));return;}

                World w = p.getWorld();
                Arrow a = (Arrow) w.spawnEntity(p.getEyeLocation(), EntityType.ARROW);
                a.setVelocity(p.getEyeLocation().getDirection().multiply(4));

                Yungchai.BOW.set(p, 7);
                p.setCooldown(Material.BOW, 7*20);
            }
        }
        if ((event.getAction() == Action.LEFT_CLICK_AIR) | (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            ItemStack inHand = event.getPlayer().getInventory().getItemInMainHand();
            Player p = event.getPlayer();
            if (inHand.isSimilar(HUNTER_BOW())) {
                World w = p.getWorld();
                Arrow a = (Arrow) w.spawnEntity(p.getEyeLocation(), EntityType.ARROW);
                a.setVelocity(p.getEyeLocation().getDirection().multiply(2));
            }
        }
    }

    @EventHandler
    public void InteractAtEntity(PlayerInteractEntityEvent event) {
        Player p = event.getPlayer();
        if (event.getRightClicked() instanceof Player) {
            Player c = (Player) event.getRightClicked();
            if (p.getInventory().getItemInMainHand().isSimilar(HUNTER_AXE())) {
                if (!Yungchai.AXE.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Hunter's Chain Swing is on cooldown (for %d seconds)", Yungchai.AXE.get(p)));return;}

                for (int i = 0; i < 9; i++) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () ->  {
                        c.damage(1);
                        c.setNoDamageTicks(0);
                        Vector n = c.getVelocity().add(new Vector(0, 0.1, 0));
                        c.setVelocity(n);
                    }, i*3);
                }

                Yungchai.AXE.set(p, 30);
                p.setCooldown(Material.STONE_AXE, 30*20);

            }
        }
    }


    public static ItemStack SWORDSMAN_SWORD() {
        ItemStack a = new ItemStack(Material.STONE_SWORD, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Heavyblade");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity " + ChatColor.GRAY + "Common");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "RMB to Pump Adrenaline");
        b.setUnbreakable(true);
        b.setLore(lore);
        b.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        a.setItemMeta(b);
        return a;
    }

    public static ItemStack SWORDSMAN_SHIELD() {
        ItemStack a = new ItemStack(Material.SHIELD, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Pavise Shield");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity " + ChatColor.GRAY + "Common");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "RMB to Charge");
        b.setLore(lore);
        b.setUnbreakable(true);
        b.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        a.setItemMeta(b);
        return a;
    }

    public static ItemStack HUNTER_AXE() {
        ItemStack a = new ItemStack(Material.STONE_AXE, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Forest Axe");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity " + ChatColor.GRAY + "Common");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "RMB to Chain Swing");
        b.setLore(lore);
        b.setUnbreakable(true);
        b.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        a.setItemMeta(b);
        return a;
    }

    public static ItemStack HUNTER_BOW() {
        ItemStack a = new ItemStack(Material.BOW, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + "Longbow");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity " + ChatColor.GRAY + "Common");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "RMB to Piercing Shot");
        b.setLore(lore);
        b.setUnbreakable(true);
        b.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        a.setItemMeta(b);
        return a;
    }




}
