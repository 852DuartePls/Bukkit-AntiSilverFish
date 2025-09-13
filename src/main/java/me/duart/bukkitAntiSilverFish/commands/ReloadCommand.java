package me.duart.bukkitAntiSilverFish.commands;

import me.duart.bukkitAntiSilverFish.AntiSilverFish;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {

    private final AntiSilverFish plugin;
    private static final String permission = "antisilverfish.admin";
    private static final List<String> reload = Collections.singletonList("reload");
    private static final String prefix = ChatColor.AQUA + "[" + ChatColor.WHITE + "AntiSilverFish" + ChatColor.AQUA + "] ";

    public ReloadCommand(AntiSilverFish plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command command, @Nonnull String string, @Nonnull String[] args) {
        if (sender.hasPermission(permission)) {

            if (args.length == 0) {
                sender.sendMessage(prefix + ChatColor.WHITE + " Version: " + ChatColor.AQUA + "v" + plugin.getDescription().getVersion());
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.onReload();
                sender.sendMessage(prefix + ChatColor.GREEN + " Config reloaded!");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@Nonnull CommandSender sender,@Nonnull Command command,@Nonnull String string,@Nonnull String[] args) {
        return args.length == 1 && sender.hasPermission(permission)
        ? reload
        : Collections.emptyList();
    }
}
