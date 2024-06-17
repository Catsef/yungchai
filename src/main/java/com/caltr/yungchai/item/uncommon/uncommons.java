package com.caltr.yungchai.item.uncommon;

import com.caltr.yungchai.Yungchai;
import com.caltr.yungchai.util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        return UNCOMMON_ITEM("Dark Magic", "Darkness", Material.POTION, "Painful.", "Psychotic Blast");
    }
    public static ItemStack HEALER_NEEDLE () {
        return UNCOMMON_ITEM("Injection Needle", "Extra Heart", Material.BLAZE_ROD, "Sanitary.", "Heal");
    }

    public static ItemStack GLADIATOR_BLADE () {
        return UNCOMMON_ITEM("Gladius", "Strengthen", Material.END_ROD, "Made of Crystal", "Slice");
    }
    public static ItemStack GLADIATOR_DAGGER () {
        return UNCOMMON_ITEM("Dagger", "Bleed", Material.WOODEN_SWORD, "Very sharp blade.", "Stab");
    }
}
