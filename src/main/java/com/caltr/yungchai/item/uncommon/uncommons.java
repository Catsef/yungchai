package com.caltr.yungchai.item.uncommon;

import com.caltr.yungchai.Yungchai;
import com.caltr.yungchai.util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.caltr.yungchai.util.smsg;

public class uncommons implements Listener {

    public net.md_5.bungee.api.ChatColor lime = util.asCol("#91ebba");
    private final Plugin plugin;
    public uncommons (Plugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onInteract (PlayerInteractEvent event) {
         // rclick at air / at ground
        if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(HEALER_POT())) {
                event.setCancelled(true);
            }
        }

        if ((event.getAction() == Action.LEFT_CLICK_BLOCK) | (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(HEALER_NEEDLE())) {
                Player p = event.getPlayer();
                UUID uuid = p.getUniqueId();
                if (!Yungchai.NEEDLE_COUNTER.check(uuid)) {Yungchai.NEEDLE_COUNTER.push(uuid);}
                Yungchai.NEEDLE_COUNTER.increment(uuid);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 10, Yungchai.NEEDLE_COUNTER.get(uuid)-7);
                if (!(Yungchai.NEEDLE_COUNTER.get(uuid) == 9)) {return;}
                Yungchai.NEEDLE_COUNTER.set(uuid, 0);
                p.setHealth(p.getHealth()+1);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 10, 7);


            }
        }

    }

    @EventHandler
    public void InteractAtEntity(PlayerInteractEntityEvent event) {
        // rclick at player
        Player p = event.getPlayer();
        if (event.getRightClicked() instanceof Player) {
            Player c = (Player) event.getRightClicked();
            ItemStack inHand = p.getInventory().getItemInMainHand();
            if (p.getInventory().getItemInMainHand().isSimilar(HEALER_POT())) {
                if (!Yungchai.POT.check(p)) {smsg(p, "Healer's Darkness", Yungchai.POT.get(p));return;}
                c.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 2));

                event.setCancelled(true);

                Yungchai.POT.set(p, 10);
                p.setCooldown(Material.POTION, 10*20);
                }
            }
    }

    @EventHandler
    public void EntityDamageEntity(EntityDamageByEntityEvent event) {
        // lclick at player
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player p = (Player) event.getDamager();
            Player c = (Player) event.getEntity();

            if (p.getInventory().getItemInMainHand().isSimilar(HEALER_POT())) {
                c.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 2));
                p.setVelocity(p.getEyeLocation().getDirection().multiply(-1.6));
            }
        }
    }


    public static ItemStack UNCOMMON_ITEM (String name, String rmbPower, Material item, String quote, String lmbPower) {
        net.md_5.bungee.api.ChatColor lime = util.asCol("#91ebba");
        ItemStack a = new ItemStack(item, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(lime + "" + ChatColor.BOLD + name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity " + lime + "Common");
        lore.add(lime + "" + ChatColor.BOLD + "RMB to " + rmbPower);
        lore.add(lime + "" + ChatColor.BOLD + "LMB to " + lmbPower);
        lore.add(ChatColor.ITALIC + quote);
        b.setUnbreakable(true);
        b.setLore(lore);
        b.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        a.setItemMeta(b);
        return a;
    }

    public static ItemStack HEALER_POT () {
        return UNCOMMON_ITEM("Dark Magic", "Darkness", Material.POTION, "Painful L[0s] R[10s]", "Psychotic Blast");
    }
    public static ItemStack HEALER_NEEDLE () {
        return UNCOMMON_ITEM("Injection Needle", "Extra Heart", Material.BLAZE_ROD, "Heals R[10s 40s] L[0s]", "Heal");
    }

    public static ItemStack GLADIATOR_BLADE () {
        return UNCOMMON_ITEM("Gladius", "Strengthen", Material.END_ROD, "Sword", "Slice");
    }
    public static ItemStack GLADIATOR_DAGGER () {
        return UNCOMMON_ITEM("Dagger", "Bleed", Material.WOODEN_SWORD, "Dagger", "Stab");
    }
}
