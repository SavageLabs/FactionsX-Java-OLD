package io.illyria.factionsx.utils.hooks;

import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SuperVanishHook {

    public static boolean isVanished(Player player) {
        if (!isSetup()) return false;
        return VanishAPI.isInvisible(player);
    }

    public static List<UUID> getVanishedPlayers() {
        if (!isSetup()) return new ArrayList<>();
        return VanishAPI.getInvisiblePlayers();
    }

    public static boolean isSetup() {
        return HookManager.getInstance().getEnabledHooks().contains("SuperVanish") || HookManager.getInstance().getEnabledHooks().contains("PremiumVanish");
    }

}
