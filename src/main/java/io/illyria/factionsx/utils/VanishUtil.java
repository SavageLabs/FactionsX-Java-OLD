package io.illyria.factionsx.utils;

import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.utils.hooks.EssentialsHook;
import io.illyria.factionsx.utils.hooks.SuperVanishHook;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class VanishUtil {

    public static boolean isVanished(Player player) {
        if (EssentialsHook.isSetup() && EssentialsHook.isVanished(player)) return true;
        if (SuperVanishHook.isSetup() && SuperVanishHook.isVanished(player)) return true;
        return hasVanishedMetadata(player);
    }

    public static boolean isVanished(Player player, Player target) {
        if (isVanished(target)) return true;
        return (Config.VANISH_CHECK_CANSEE.getBoolean() && !player.canSee(target));
    }

    private static boolean hasVanishedMetadata(Player player) {
        if (!player.hasMetadata("vanished")) {
            return false;
        }
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta == null) continue;
            if (meta.asBoolean()) {
                return true;
            }
        }
        return false;
    }

}
