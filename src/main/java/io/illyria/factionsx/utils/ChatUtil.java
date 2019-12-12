package io.illyria.factionsx.utils;

import io.illyria.factionsx.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

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

}
