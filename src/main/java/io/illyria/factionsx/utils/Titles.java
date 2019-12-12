package io.illyria.factionsx.utils;

/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Crypto Morin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
import com.google.common.base.Strings;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/*
 * References
 *
 * * * GitHub: https://github.com/CryptoMorin/XSeries/blob/master/Titles.java
 * * XSeries: https://www.spigotmc.org/threads/378136/
 * PacketPlayOutTitle: https://wiki.vg/Protocol#Title
 *
 */

/**
 * A reflection API for titles in Minecraft.
 * Fully optimized - Supports 1.8.8+ and above.
 * Requires ReflectionUtils.
 * Messages are not colorized by default.
 * <p>
 * Titles are text messages that appear in the
 * middle of the players screen: https://minecraft.gamepedia.com/Commands/title
 * PacketPlayOutTitle: https://wiki.vg/Protocol#Title
 *
 * @author Crypto Morin
 * @version 1.0.0
 * @see ReflectionUtils
 */
public class Titles {
    /**
     * Used for the "stay" feature of titles.
     */
    private static final Object TIMES;
    private static final Object TITLE;
    private static final Object SUBTITLE;
    private static final Object CLEAR;

    /**
     * PacketPlayOutTitle Types: TITLE, SUBTITLE, ACTIONBAR, TIMES, CLEAR, RESET;
     */
    private static final MethodHandle PACKET;
    /**
     * ChatComponentText JSON message builder.
     */
    private static final MethodHandle CHAT_COMPONENT_TEXT;

    static {
        Class<?> chatComponentText = ReflectionUtils.getNMSClass("ChatComponentText");
        Class<?> packet = ReflectionUtils.getNMSClass("PacketPlayOutTitle");
        Class<?> titleTypes = packet.getDeclaredClasses()[0];
        MethodHandle packetCtor = null;
        MethodHandle chatComp = null;

        Object times = null;
        Object title = null;
        Object subtitle = null;
        Object clear = null;

        for (Object type : titleTypes.getEnumConstants()) {
            switch (type.toString()) {
                case "TIMES":
                    times = type;
                    break;
                case "TITLE":
                    title = type;
                    break;
                case "SUBTITLE":
                    subtitle = type;
                    break;
                case "CLEAR":
                    clear = type;
            }
        }

        try {
            chatComp = MethodHandles.lookup().findConstructor(chatComponentText,
                    MethodType.methodType(void.class, String.class));

            packetCtor = MethodHandles.lookup().findConstructor(packet,
                    MethodType.methodType(void.class, titleTypes,
                            ReflectionUtils.getNMSClass("IChatBaseComponent"),
                            int.class, int.class, int.class));
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

        TITLE = title;
        SUBTITLE = subtitle;
        TIMES = times;
        CLEAR = clear;

        PACKET = packetCtor;
        CHAT_COMPONENT_TEXT = chatComp;
    }

    /**
     * Sends a title message with title and subtitle to a player.
     *
     * @param player   the player to send the title to.
     * @param fadeIn   the amount of ticks for title to fade in.
     * @param stay     the amount of ticks for the title to stay.
     * @param fadeOut  the amount of ticks for the title to fade out.
     * @param title    the title message.
     * @param subtitle the subtitle message.
     * @see #clearTitle(Player)
     * @since 1.0.0
     */
    public static void sendTitle(@Nonnull Player player,
                                 int fadeIn, int stay, int fadeOut,
                                 @Nullable String title, @Nullable String subtitle) {
        Objects.requireNonNull(player, "Cannot send title to null player");
        if (title == null && subtitle == null) return;

        try {
            Object timesPacket = PACKET.invoke(TIMES, CHAT_COMPONENT_TEXT.invoke(title), fadeIn, stay, fadeOut);
            ReflectionUtils.sendPacket(player, timesPacket);

            if (title != null) {
                Object titlePacket = PACKET.invoke(TITLE, CHAT_COMPONENT_TEXT.invoke(title), fadeIn, stay, fadeOut);
                ReflectionUtils.sendPacket(player, titlePacket);
            }
            if (subtitle != null) {
                Object subtitlePacket = PACKET.invoke(SUBTITLE, CHAT_COMPONENT_TEXT.invoke(subtitle), fadeIn, stay, fadeOut);
                ReflectionUtils.sendPacket(player, subtitlePacket);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Sends a title message with title and subtitle with normal
     * fade in, stay and fade out time to a player.
     *
     * @param player   the player to send the title to.
     * @param title    the title message.
     * @param subtitle the subtitle message.
     * @see #sendTitle(Player, int, int, int, String, String)
     * @since 1.0.0
     */
    public static void sendTitle(@Nonnull Player player, @Nonnull String title, @Nonnull String subtitle) {
        sendTitle(player, 10, 20, 10, title, subtitle);
    }

    /**
     * Parses and sends a title from the config.
     * The configuration section must at least
     * contain {@code title} or {@code subtitle}
     *
     * <p>
     * <b>Example:</b>
     * <pre><quoteblock>
     *     ConfigurationSection titleSection = plugin.getConfig().getConfigurationSection("restart-title");
     *     Titles.sendTitle(player, titleSection);
     * </quoteblock></pre>
     *
     * @param player the player to send the title to.
     * @param config the configuration section to parse the title properties from.
     * @since 1.0.0
     */
    public static void sendTitle(@Nonnull Player player, @Nonnull ConfigurationSection config) {
        String title = config.getString("title");
        String subtitle = config.getString("subtitle");

        int fadeIn = config.getInt("fade-in");
        int stay = config.getInt("stay");
        int fadeOut = config.getInt("fade-out");

        if (fadeIn < 1) fadeIn = 10;
        if (stay < 1) stay = 20;
        if (fadeOut < 1) fadeOut = 10;

        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    /**
     * Clears the title and subtitle message from the player's screen.
     *
     * @param player the player to clear the title from.
     * @since 1.0.0
     */
    public static void clearTitle(Player player) {
        Objects.requireNonNull(player, "Cannot clear title from null player");
        Object clearPacket = null;

        try {
            clearPacket = PACKET.invoke(CLEAR, null, -1, -1, -1);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        ReflectionUtils.sendPacket(player, clearPacket);
    }

    /**
     * Changes the tablist header and footer message for a player.
     *
     * @param player the player to change the tablist for.
     * @param header the header of the tablist.
     * @param footer the footer of the tablist.
     * @apiNote This is not fully completed as it's not used alot.
     * @since 1.0.0
     */
    public static void sendTabList(Player player, String header, String footer) {
        Objects.requireNonNull(player, "Cannot update tab for null player");
        header = Strings.isNullOrEmpty(header) ?
                "" : ChatColor.translateAlternateColorCodes('&', header).replace("%player%", player.getDisplayName());
        footer = Strings.isNullOrEmpty(footer) ?
                "" : ChatColor.translateAlternateColorCodes('&', footer).replace("%player%", player.getDisplayName());

        try {
            Method chatComponentBuilderMethod = ReflectionUtils.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class);
            Object tabHeader = chatComponentBuilderMethod.invoke(null, "{\"text\":\"" + header + "\"}");
            Object tabFooter = chatComponentBuilderMethod.invoke(null, "{\"text\":\"" + footer + "\"}");
            Object packet = ReflectionUtils.getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor().newInstance();

            Field aField;
            Field bField;
            try {
                aField = packet.getClass().getDeclaredField("a");
                bField = packet.getClass().getDeclaredField("b");
            } catch (Exception ex) {
                aField = packet.getClass().getDeclaredField("header");
                bField = packet.getClass().getDeclaredField("footer");
            }

            aField.setAccessible(true);
            aField.set(packet, tabHeader);

            bField.setAccessible(true);
            bField.set(packet, tabFooter);

            ReflectionUtils.sendPacket(player, packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
