package io.illyria.factionsx.utils.hooks;

import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.utils.ChatUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private FactionsBootstrap plugin;
    private static PlaceholderAPIHook papiExt;

    PlaceholderAPIHook(FactionsBootstrap plugin) {
        this.plugin = plugin;
        papiExt = this;
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

    static void unreg() {
        if (isSetup())
            PlaceholderAPI.unregisterExpansion(papiExt);
    }

    public String parse(String str, OfflinePlayer p) {
        if (str.length() <= 0 || !str.contains("%") || !isSetup()) return str;
        return PlaceholderAPI.setPlaceholders(p, str);
    }

    public List<String> parse(List<String> str, OfflinePlayer p) {
        if (str.isEmpty() || !isSetup()) return str;
        List<String> parsed = new ArrayList<>();
        for (String string : str) {
            parsed.add(ChatUtil.color(PlaceholderAPI.setPlaceholders(p, string)));
        }
        return parsed;
    }

    @Override
    public String onRequest(OfflinePlayer p, String s) {
        // %factionsx_version% - Displays FactionsX version
        if (s.equalsIgnoreCase("version")) {
            return getVersion();
        }
        return Message.PAPI_ERROR.getMessage();
    }

    public static PlaceholderAPIHook getPapiExt() { return papiExt; }

    public static boolean isSetup() {
        return papiExt != null;
    }

}