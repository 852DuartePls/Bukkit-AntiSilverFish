package me.duart.bukkitAntiSilverFish;

import me.duart.bukkitAntiSilverFish.commands.ReloadCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public final class AntiSilverFish extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        ReloadCommand reloadCommand = new ReloadCommand(this);

        PluginCommand pluginCommand = getCommand("antisilverfish");
        if (pluginCommand != null) {
            pluginCommand.setExecutor(reloadCommand);
            pluginCommand.setTabCompleter(reloadCommand);
        }

        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onReload() {
        reloadConfig();
    }

    // Prevent Spawning from Infested Blocks
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onSilverFishSpawn(@Nonnull CreatureSpawnEvent event) {
        if (event.getEntityType() != EntityType.SILVERFISH) return;
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK) return;
        if (!getConfig().getBoolean("Disable_Silverfish_Spawning", true)) return;
        event.setCancelled(true);
    }

    // Prevent them from re-entering blocks
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onSilverFishEnterBlock(@Nonnull EntityChangeBlockEvent event) {
        if (event.getEntityType() != EntityType.SILVERFISH) return;
        if (!getConfig().getBoolean("Disable_Silverfish_Reentering", true)) return;
        event.setCancelled(true);
    }

    // Prevent the Infestation potion from working
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onSilverFishInfestation(@Nonnull EntityPotionEffectEvent event) {
        if (!getConfig().getBoolean("Disable_Infestation_Potions", true)) return;
        if (event.getCause() != EntityPotionEffectEvent.Cause.ARROW
                && event.getCause() != EntityPotionEffectEvent.Cause.POTION_SPLASH
                && event.getCause() != EntityPotionEffectEvent.Cause.AREA_EFFECT_CLOUD) return;
        if (event.getNewEffect() == null || event.getNewEffect().getType() != PotionEffectType.INFESTED) return;
        event.setCancelled(true);
    }
}