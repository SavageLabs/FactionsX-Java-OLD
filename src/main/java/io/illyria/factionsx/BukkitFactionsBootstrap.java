package io.illyria.factionsx;

import io.illyria.factionsx.config.Config;
import io.illyria.factionsx.core.Permission;
import io.illyria.factionsx.internal.FactionsBootstrap;
import io.illyria.factionsx.utils.ChatUtil;
import io.illyria.factionsx.utils.hooks.HookManager;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Here is the bukkit implementation of Factions plugin.
 * Anything related to bukkit goes here.
 */

public final class BukkitFactionsBootstrap extends JavaPlugin implements FactionsBootstrap {

    private static BukkitFactionsBootstrap bukkitFactionsBootstrap;
    private FactionsX factionsX = new FactionsX(this);

    private HookManager hookManager;

    @Override
    public void onEnable() {
        bukkitFactionsBootstrap = this;
        printLogo();
        // Suggest using Paper for better performance
        PaperLib.suggestPaper(this);
        // Load configs
        loadConfig();
        factionsX.enable();
        // Load hooks
        hookManager = HookManager.getInstance();
        hookManager.loadHooks();
        // Register permissions
        Permission.registerAllPermissions(this.getServer().getPluginManager());
    }

    @Override
    public void onDisable() {
        // Cancel running Tasks, this should happen anyways
        Bukkit.getServer().getScheduler().cancelTasks(this);
        // Unregister hooks
        hookManager.unregisterHooks();
        // Set the saved instance to null, saving memory
        bukkitFactionsBootstrap = null;
        factionsX.disable();
    }

    @Override
    public String getVersion() {
        return Bukkit.getVersion();
    }

    @Override
    public void unload() {

    }

    public void loadConfig() {
        factionsX.getConfigManager().getFileMap().get("config").init();
        factionsX.getConfigManager().getFileMap().get("messages_" + Config.LOCALE.getString()).init();
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, bukkitFactionsBootstrap);
        }
    }

    // Tell IntelliJ to not format this, by enabling formatter markers in comments (Pref-> Editor-> Code Style)
    // Made this way for easy editing/char replacing, using equal size chars for all consoles compatibility.
    //@formatter:off
    private void printLogo() {
        ChatUtil.sendConsole(("\n"+
            "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬛⬛⬛\n" +
            "⬜⬛⬛⬛⬛⬛⬜⬜⬜⬜⬛⬜⬜⬛⬛⬛⬛⬜⬛⬛⬛⬛⬛⬜⬛⬛⬛⬜⬜⬛⬛⬛⬜⬛⬜⬜⬛⬛⬜⬜⬛⬛⬛⬜⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬜\n" +
            "⬜⬛⬛⬜⬜⬛⬜⬜⬜⬛⬛⬜⬜⬛⬜⬛⬛⬜⬜⬜⬛⬜⬛⬜⬜⬛⬜⬜⬛⬛⬜⬛⬜⬛⬛⬜⬜⬛⬜⬛⬛⬜⬜⬜⬜⬜⬜⬛⬛⬜⬛⬛⬜⬜⬜\n" +
            "⬜⬛⬜⬜⬜⬜⬜⬜⬛⬜⬛⬜⬜⬛⬜⬜⬛⬜⬜⬜⬛⬜⬜⬜⬜⬛⬜⬜⬛⬜⬜⬛⬜⬛⬛⬛⬜⬛⬜⬛⬛⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜\n" +
            "⬜⬛⬛⬛⬛⬜⬜⬛⬛⬛⬛⬜⬜⬛⬜⬜⬜⬜⬜⬜⬛⬜⬜⬜⬜⬛⬜⬜⬛⬜⬜⬛⬜⬛⬜⬛⬛⬛⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜\n" +
            "⬜⬛⬜⬜⬜⬜⬛⬛⬜⬜⬛⬜⬜⬛⬜⬜⬛⬜⬜⬜⬛⬜⬜⬜⬜⬛⬜⬜⬛⬜⬛⬛⬜⬛⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬜⬜⬜⬛⬛⬜⬛⬛⬜⬜⬜\n" +
            "⬜⬛⬜⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬛⬛⬛⬛⬜⬜⬜⬛⬜⬜⬜⬛⬛⬛⬜⬛⬛⬛⬜⬜⬛⬛⬜⬜⬛⬜⬛⬛⬛⬜⬜⬜⬜⬛⬛⬜⬜⬜⬛⬛⬜⬜\n" +
            "⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬜⬛⬛⬛⬜⬜⬜⬜⬜⬛⬛⬛\n"
        ).replace("⬜","&0█").replace("⬛","&f█"));
        ChatUtil.sendConsole("\n&f&l> &eMade with &4♥ &eby the&f illyria.io Team\n");
    }
    //@formatter:on

    @Override
    public File getBootstrapDataFolder() {
        return this.getDataFolder();
    }

    public FactionsX getFactionsX() {
        return factionsX;
    }

    public static BukkitFactionsBootstrap getInstance() {
        return bukkitFactionsBootstrap;
    }

}
