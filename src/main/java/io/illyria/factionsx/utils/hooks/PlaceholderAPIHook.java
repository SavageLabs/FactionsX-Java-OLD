package io.illyria.factionsx.utils.hooks;


import io.illyria.factionsx.BukkitFactionsBootstrap;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.internal.FactionsBootstrap;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private FactionsBootstrap plugin;

    public PlaceholderAPIHook(FactionsBootstrap plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "factionsx";
    }

    @Override
    public String getAuthor() {
        return ((JavaPlugin) plugin).getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return ((JavaPlugin) plugin).getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    public static void unreg(PlaceholderExpansion exp) {
        PlaceholderAPI.unregisterExpansion(exp);
    }

    @Override
    public String onRequest(OfflinePlayer p, String s) {
        // %factionsx_version% - Displays FactionsX version
        if (s.equalsIgnoreCase("version")) {
            return getVersion();
        }
        return Message.PAPI_ERROR.getMessage();
    }

}