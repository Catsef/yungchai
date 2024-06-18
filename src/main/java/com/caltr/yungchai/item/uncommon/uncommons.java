package com.caltr.yungchai.item.uncommon;

import com.caltr.yungchai.Yungchai;
import com.caltr.yungchai.util;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Vector3d;
import org.joml.Vector3f;

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
            ItemStack i = event.getPlayer().getInventory().getItemInMainHand();
            Player p = event.getPlayer();
            if (i.isSimilar(HEALER_POT())) {
                event.setCancelled(true);
            }
            else if (i.isSimilar(HEALER_NEEDLE())) {
                if (!Yungchai.NEEDLE.check(p)) {smsg(p, "Healer's Heart Extension", Yungchai.NEEDLE.get(p));return;}
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 400, 3));
                Yungchai.NEEDLE.set(p, 25);
                p.setCooldown(Material.BLAZE_ROD, 10*20);
            }
            else if (i.isSimilar(GLADIATOR_AXE())) {
                if (!Yungchai.HEAVER.check(p)) {smsg(p, "Gladiator's HYSTERICAL STRENGTH", Yungchai.NEEDLE.get(p));return;}
                Snowball BALL = (Snowball) p.getWorld().spawnEntity(p.getLocation(), EntityType.SNOWBALL);
                BlockDisplay BLOCK = (BlockDisplay) p.getWorld().spawnEntity(p.getLocation(), EntityType.BLOCK_DISPLAY);
                BLOCK.setBlock(p.getWorld().getBlockAt(p.getLocation().add(0, -1, 0)).getBlockData());
                BLOCK.setTransformation(new Transformation(new Vector3f(1, 1, 1), new AxisAngle4f(), new Vector3f(0.4f, 0.4f, 0.4f), new AxisAngle4f()));
                BALL.addPassenger(BLOCK);
                BALL.setVelocity(p.getEyeLocation().getDirection().multiply(3));
                Yungchai.HEAVER_TRACKER.push(BALL, p);

            }
        }

        if ((event.getAction() == Action.LEFT_CLICK_BLOCK) | (event.getAction() == Action.LEFT_CLICK_BLOCK)) {
            if (event.getPlayer().getInventory().getItemInMainHand().isSimilar(HEALER_NEEDLE())) {
                Player p = event.getPlayer();
                UUID uuid = p.getUniqueId();
                if (!Yungchai.NEEDLE_COUNTER.check(uuid)) {Yungchai.NEEDLE_COUNTER.push(uuid);}
                Yungchai.NEEDLE_COUNTER.increment(uuid);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 10, (float) (Yungchai.NEEDLE_COUNTER.get(uuid) - 7) /7);
                if (!(Yungchai.NEEDLE_COUNTER.get(uuid) == 5)) {return;}
                Yungchai.NEEDLE_COUNTER.set(uuid, 0);
                p.setHealth(p.getHealth()+1);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_COPPER_BULB_TURN_ON, 10, 7);
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

    @EventHandler
    public void ProjectileHit(ProjectileHitEvent event) {
        // some projectile hit
        if (event.getEntity() instanceof Snowball) {
            if (event.getEntity().getPassengers().get(0) != null && event.getEntity().getPassengers().get(0) instanceof BlockDisplay) {
                if (event.getHitEntity() == null) { event.getHitBlock().getWorld().createExplosion(event.getHitBlock().getLocation(), 4f, false, true); return; }
                event.getHitEntity().getLocation().getWorld().createExplosion(event.getHitEntity().getLocation(), 6f, false, true);

                event.getEntity().getPassengers().clear();
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
        return UNCOMMON_ITEM("Injection Needle", "Heart Extension", Material.BLAZE_ROD, "Heals R[10s 40s] L[9x]", "Heal");
    }

    public static ItemStack GLADIATOR_BLADE () {
        return UNCOMMON_ITEM("Gladius", "Defend", Material.END_ROD, "Sword", "Slice");
    }
    public static ItemStack GLADIATOR_AXE () {
        return UNCOMMON_ITEM("Heaver", "HYSTERICAL STRENGTH", Material.STONE_SHOVEL, "???", "n/a");
    }
}
