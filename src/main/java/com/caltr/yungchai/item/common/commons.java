package com.caltr.yungchai.item.common;

import com.caltr.yungchai.Yungchai;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

            }
            else if (inHand.isSimilar(SWORDSMAN_SHIELD())) {

                if (!Yungchai.SHIELD.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Swordsman's Charge is on cooldown (for %d seconds)", Yungchai.SHIELD.get(p)));return;}

                Vector v = p.getEyeLocation().getDirection();
                p.setVelocity(v.multiply(3));

                Yungchai.SHIELD.set(p, 10);
                p.setCooldown(Material.SHIELD, 10*20);

            }
            else if (inHand.isSimilar(HUNTER_BOW())) {

                if (!Yungchai.BOW.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Hunter's Piercing Shot is on cooldown (for %d seconds)", Yungchai.BOW.get(p)));return;}

                World w = p.getWorld();
                Arrow a = (Arrow) w.spawnEntity(p.getEyeLocation(), EntityType.ARROW);
                a.setVelocity(p.getEyeLocation().getDirection().multiply(4));

                Yungchai.BOW.set(p, 7);
                p.setCooldown(Material.BOW, 7*20);
            }
            else if (inHand.isSimilar(BRAWLER_CHAIN())) {
                if (!Yungchai.CHAIN.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Brawler's Attach and Pull is on cooldown (for %d seconds)", Yungchai.CHAIN.get(p)));return;}

                List<Entity> nearEntities = p.getNearbyEntities(15, 15, 15);
                double BufferDistance = 100_000_000;
                Player iterator;
                Player candidate = null;
                for (Entity nearEntity : nearEntities) {
                    if (nearEntity instanceof Player) {
                        iterator = (Player) nearEntity;
                        double DistanceBetweenPlayerAndIterator = p.getLocation().distance(iterator.getLocation());
                        if (BufferDistance > DistanceBetweenPlayerAndIterator) {
                            BufferDistance = DistanceBetweenPlayerAndIterator;
                            candidate = iterator;
                        }
                    }
                }

                Vector dirYanked = p.getEyeLocation().getDirection().multiply(-2);
                if (candidate == null) {return;}
                candidate.setVelocity(dirYanked);
                Yungchai.CHAIN.set(p, 10);
                p.setCooldown(Material.CHAIN, 10*20);
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
            ItemStack inHand = p.getInventory().getItemInMainHand();
            if (p.getInventory().getItemInMainHand().isSimilar(HUNTER_AXE())) {
                if (!Yungchai.AXE.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Hunter's Combo Swing is on cooldown (for %d seconds)", Yungchai.AXE.get(p)));return;}

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
            else if (p.getInventory().getItemInMainHand().isSimilar(BRAWLER_GLOVE())) {
                if (!Yungchai.GLOVE.check(p)) {p.sendMessage(String.format(ChatColor.RED + "Brawler's Uppercut is on cooldown (for %d seconds)", Yungchai.GLOVE.get(p)));return;}
                c.damage(4);
                c.setVelocity(c.getVelocity().add(new Vector(0, 0.6, 0)));
                Yungchai.GLOVE.set(p, 10);
                p.setCooldown(Material.CLAY_BALL, 10*20);
            }
        }
    }

    @EventHandler
    public void EntityDamageEntity(EntityDamageByEntityEvent event) {
        Entity e = event.getEntity();
        Entity d = event.getDamager();

        if ((e instanceof Player) && (d instanceof Player)) {
            Player Pe = (Player) e;
            Player Pd = (Player) d;
            if (Pd.getInventory().getItemInMainHand().isSimilar(BRAWLER_GLOVE())) {
                Pe.damage(3);
                Pe.setVelocity(Pd.getEyeLocation().getDirection().multiply(1.1));
            }
        }
    }

    public static ItemStack COMMON_ITEM(String name, String rmbPower, Material item, String quote, String lmbPower) {
        ItemStack a = new ItemStack(item, 1);
        ItemMeta b = a.getItemMeta();
        b.setDisplayName(ChatColor.GRAY + "" + ChatColor.BOLD + name);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "Rarity " + ChatColor.GRAY + "Common");
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "RMB to " + rmbPower);
        lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "LMB to " + lmbPower);
        lore.add(ChatColor.ITALIC + quote);
        b.setUnbreakable(true);
        b.setLore(lore);
        b.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
        a.setItemMeta(b);
        return a;
    }



    public static ItemStack SWORDSMAN_SWORD() {
        return COMMON_ITEM("Heavyblade", "Pump Adrenaline", Material.STONE_SWORD, "[Engraving] Athena.", "n/a");
    }
    public static ItemStack SWORDSMAN_SHIELD() {
        return COMMON_ITEM("Pavise Shield", "Charge", Material.SHIELD, "Around 75 cm tall.", "n/a");
    }


    public static ItemStack HUNTER_AXE() {
        return COMMON_ITEM("Forest Axe", "Combo Swing", Material.STONE_AXE, "Chipped blade.", "n/a");
    }
    public static ItemStack HUNTER_BOW() {
        return COMMON_ITEM("Longbow", "Piercing Shot", Material.BOW, "[Engraving] R. H.", "Arrow Fire");
    }


    public static ItemStack BRAWLER_GLOVE() {
        return COMMON_ITEM("Gloves", "Uppercut", Material.CLAY_BALL, "[Marker] DOT", "Hook");
    }
    public static ItemStack BRAWLER_CHAIN() {
        return COMMON_ITEM("Metal Chains", "Attach & Pull", Material.CHAIN, "Made of steel.", "Swing");
    }




}
