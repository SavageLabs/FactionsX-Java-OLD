package io.illyria.factionsx.utils;

import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.config.Message;
import me.rayzr522.jsonmessage.JSONMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

/**
 * Methods related to messages, actionbars, titles et similia.
 * Anything related to sending text feedback should be here.
 */

public class ChatUtil {

    // - Player messaging single lined and multi lined messages.

    /**
     * Description: Sends the player a multi line message.
     *
     * @param toPlayer The player we want to send the message to.
     * @param messages Creates an list and then loops over the list then sends it to the player
     */
    public static void message(CommandSender toPlayer, String... messages){
        for (String message : messages)
            message(toPlayer, message);
    }

    /**
     * Description: Send the player a single lined coloured message.
     *
     * @param toPlayer The player that is set to receive a message.
     * @param message Single lined message that converts all colour codes and then send it to the player the question.
     */
    public static void message(CommandSender toPlayer, String message){
        toPlayer.sendMessage(color(message));
    }

    // Color messages

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    // Console Feedback messages

    public static void sendConsole(String str) {
        Bukkit.getConsoleSender().sendMessage(color(str));
    }

    public static void debug(String str) {
        if (Config.DEBUG.getBoolean())
            Bukkit.getConsoleSender().sendMessage(color(Message.PREFIX_DEBUG.getMessage() + str));
    }

    public static void error(String str) {
        Bukkit.getConsoleSender().sendMessage(color(Message.PREFIX_ERROR.getMessage() + str));
    }

    // Send Title only

    public static void sendTitle(Player[] player, String title, int fadeIn, int stay, int fadeOut) {
        sendTitle(player, title, "", fadeIn, stay, fadeOut);
    }

    public static void sendTitle(Player[] player, String title) {
        sendTitle(player, title, 10, 20, 10);
    }

    // Send Subtitle only

    public static void sendSubtitle(Player[] player, String subtitle, int fadeIn, int stay, int fadeOut) {
        sendTitle(player, "", subtitle, fadeIn, stay, fadeOut);
    }

    public static void sendSubtitle(Player[] player, String subtitle) {
        sendSubtitle(player, subtitle, 10, 20, 10);
    }

    // Send full Title+Subtitle

    public static void sendTitle(Player[] player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        JSONMessage.create(color(title != null ? title : ""))
                .title(fadeIn, stay, fadeOut, player);
        if (subtitle != null && subtitle.length() > 0) {
            JSONMessage.create(color(subtitle))
                    .subtitle(player);
        }
    }

    // Send normal ActionBar

    public static void sendActionBar(Player[] players, String message) {
        JSONMessage.actionbar(color(message), players);
    }

    // Send ActionBar with duration

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
