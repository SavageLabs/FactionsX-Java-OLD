package io.illyria.factionsx.utils.hooks;

import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.Teleport;
import com.earth2me.essentials.Trade;
import com.earth2me.essentials.User;
import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EssentialsHook {

    private static IEssentials essentials = null;

    public static boolean setup(FactionsBootstrap plugin) {
        // Just to be safe, return true if it's already setup.
        if (essentials != null) return true;
        // Just to be safe, return false if the plugin is not enabled.
        if (((JavaPlugin) plugin).getServer().getPluginManager().getPlugin("Essentials") == null) {
            return false;
        }
        essentials = (IEssentials) Bukkit.getPluginManager().getPlugin("Essentials");
        return essentials != null;
    }

    public static boolean handleTeleport(Player player, Location loc) {
        if (!Config.HOOK_ESSENTIALS.getBoolean() || !Config.HOOK_ESSENTIALS_PASSTELEPORT.getBoolean() || !isSetup())
            return false;
        Teleport teleport = essentials.getUser(player).getTeleport();
        Trade trade = new Trade(Config.PRICE_HOME_TELEPORT.getDouble(), essentials);
        try {
            teleport.teleport(loc, trade, PlayerTeleportEvent.TeleportCause.PLUGIN);
        } catch (Exception e) {
            player.sendMessage(ChatUtil.color(e.getMessage()));
        }
        return true;
    }

    public static boolean isVanished(Player player) {
        if (player == null || !isSetup()) return false;
        User user = essentials.getUser(player);
        return user != null && user.isVanished();
    }

    public static boolean isSetup() {
        return essentials != null;
    }

}
