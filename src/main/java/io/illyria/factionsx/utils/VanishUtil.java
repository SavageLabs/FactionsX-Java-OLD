package io.illyria.factionsx.utils;

import io.illyria.factionsx.utils.hooks.EssentialsHook;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

public class VanishUtil {

    // TODO: add supervanish compatibility

    public static boolean isVanished(Player player) {
        if (EssentialsHook.isSetup() && EssentialsHook.isVanished(player)) return true;
        return hasVanishedMetadata(player);
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
