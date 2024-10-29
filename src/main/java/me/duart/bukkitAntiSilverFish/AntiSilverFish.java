package me.duart.bukkitAntiSilverFish;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;

public final class AntiSilverFish extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    // Prevent Spawning from Infested Blocks
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onSilverFishSpawn(@Nonnull CreatureSpawnEvent event) {
        if (event.getEntityType() != EntityType.SILVERFISH) return;
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK) return;
        event.setCancelled(true);
    }

    // Prevent them from re-entering blocks
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onSilverFishEnterBlock(@Nonnull EntityChangeBlockEvent event) {
        if (event.getEntityType() != EntityType.SILVERFISH) return;
        event.setCancelled(true);
    }
}