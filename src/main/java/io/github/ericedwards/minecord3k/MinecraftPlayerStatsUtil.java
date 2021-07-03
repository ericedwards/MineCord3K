package io.github.ericedwards.minecord3k;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.util.Collection;

public class MinecraftPlayerStatsUtil {

    public static String getStats() {
        Server server = Bukkit.getServer();
        StringBuilder stats = new StringBuilder();
        Collection<? extends Player> players = server.getOnlinePlayers();
        for (Player player : players) {
            // Name World X Y Z Health
            stats.append(player.getDisplayName());
            stats.append(' ');
            stats.append(player.getWorld().getName());
            Location location = player.getLocation();
            stats.append(String.format(" x:%.2f", location.getX()));
            stats.append(String.format(" y:%.2f", location.getY()));
            stats.append(String.format(" z:%.2f", location.getZ()));
            stats.append(String.format(" health:%.2f", player.getHealth()));
            stats.append('\n');
        }
        return stats.toString();
    }

}
