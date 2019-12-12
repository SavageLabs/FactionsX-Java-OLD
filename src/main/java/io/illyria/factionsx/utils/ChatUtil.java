package io.illyria.factionsx.utils;

import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.config.Config;
import me.rayzr522.jsonmessage.JSONMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static void sendConsole(String str) {
        Bukkit.getConsoleSender().sendMessage(color(str));
    }

    public static void debug(String str) {
        if (Config.DEBUG.getBoolean())
            Bukkit.getConsoleSender().sendMessage(color(str));
    }

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }


    // Send normal ActionBar

    public static void sendActionBar(Player player, String message) {
        sendActionBar(player, message);
    }

    public static void sendActionBar(Player[] players, String message) {
        JSONMessage.actionbar(color(message), players);
    }

    // Send ActionBar with duration

    public static void sendActionBar(Player player, String message, int duration) {
        sendActionBar(player, message, duration);
    }

    public static void sendActionBar(Player[] players, String message, int duration) {
        sendActionBar(players, message);
        if (duration >= 0) {
            // Allow ActionBar messages to be shorter than 3 seconds.
            Bukkit.getScheduler().runTaskLater(BukkitFactionsBootstrap.getInstance(), () -> sendActionBar(players, ""), duration + 1);
        }
        // Re-send ActionBar every 3 seconds so it doesn't go away.
        while (duration > 40) {
            duration -= 40;
            Bukkit.getScheduler().runTaskLater(BukkitFactionsBootstrap.getInstance(), () -> sendActionBar(players, message), duration);
        }
    }

}
