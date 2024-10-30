package me.duart.bukkitAntiSilverFish.commands;

import me.duart.bukkitAntiSilverFish.AntiSilverFish;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {
    private final AntiSilverFish plugin;
    private final String permission = "antisilverfish.admin";

    public ReloadCommand(AntiSilverFish plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String string, @Nonnull String[] args) {
        if (sender.hasPermission(permission)) {
            if (args.length == 0) {
                sender.sendMessage(
                        ChatColor.AQUA + "[" + ChatColor.WHITE + "AntiSilverFish" + ChatColor.AQUA + "]"
                                + ChatColor.WHITE + " Version: "
                                + ChatColor.AQUA + "v" + plugin.getDescription().getVersion());
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.onReload();
                sender.sendMessage(ChatColor.AQUA + "[" + ChatColor.WHITE + "AntiSilverFish" + ChatColor.AQUA + "]"
                        + ChatColor.GREEN + " Config reloaded!");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender,@Nonnull Command command,@Nonnull String string,@Nonnull String[] args) {
        if (sender.hasPermission(permission)) {
            if (args.length == 1) {
                return List.of("reload");
            }
        }
        return List.of();
    }
}
