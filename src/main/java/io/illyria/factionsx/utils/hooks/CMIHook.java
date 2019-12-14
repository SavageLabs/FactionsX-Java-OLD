package io.illyria.factionsx.utils.hooks;

import com.Zrips.CMI.CMI;
import org.bukkit.entity.Player;

public class CMIHook {

    private CMIHook() {
        throw new AssertionError("Instantiating utility class.");
    }

    public static boolean isVanished(Player player) {
        return CMI.getInstance().getVanishManager().getAllVanished().contains(player.getUniqueId());
    }

    public static boolean isSetup() {
        return HookManager.getInstance().getEnabledHooks().contains("CMI");
    }

}
