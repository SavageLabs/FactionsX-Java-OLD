package io.illyria.factionsx.utils;

import io.illyria.factionsx.FactionsX;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.internal.FactionsBootstrap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

    private static Map<YamlConfiguration, File> getYamlConfiguration(String pathToFile) {
        File file = new File(pathToFile);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ChatUtil.error(Message.ERROR_FILE_IO.getMessage().replace("{file}", pathToFile));
                ex.printStackTrace();
                return null;
            }
        }
        YamlConfiguration itemFile = new YamlConfiguration();
        try {
            itemFile.load(file);
        } catch (IOException | InvalidConfigurationException | NullPointerException ex) {
            ChatUtil.error(Message.ERROR_FILE_LOAD.getMessage().replace("{file}", pathToFile));
            ex.printStackTrace();
            return null;
        }
        Map<YamlConfiguration, File> itemMap = new HashMap<>();
        itemMap.put(itemFile, file);
        return itemMap;
    }

    public static void loadLocale(FactionsBootstrap plugin, String locale) {
        String subFolder = "Translations";
        File folder = new File(FactionsX.getFactionsX().getFactionsBootstrap().getBootstrapDataFolder(), subFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
        FileConfiguration defaultLocale = new YamlConfiguration();
        Charset inputCharset = StandardCharsets.UTF_8;
        try (InputStreamReader isr = new InputStreamReader(((JavaPlugin) plugin).getResource("messages_" + locale + ".yml"), inputCharset)) {
            defaultLocale.load(isr);
        } catch (InvalidConfigurationException ex) {
            ChatUtil.error(Message.ERROR_FILE_LOAD.getMessage().replace("{file}", "messages_" + locale + ".yml"));
            ex.printStackTrace();
            return;
        } catch (IOException ex) {
            ChatUtil.error(Message.ERROR_FILE_IO.getMessage().replace("{file}", "messages_" + locale + ".yml"));
            ex.printStackTrace();
            return;
        }
        Map<YamlConfiguration, File> langMap = getYamlConfiguration(FactionsX.getFactionsX().getFactionsBootstrap().getBootstrapDataFolder() + File.separator + subFolder + File.separator + "messages_" + locale + ".yml");
        if (langMap == null) {
            return;
        }
        YamlConfiguration langYaml = langMap.entrySet().iterator().next().getKey();
        File langFile = langMap.entrySet().iterator().next().getValue();
        if (langYaml == null) {
            return;
        }
        langYaml.addDefaults(defaultLocale);
        langYaml.options().copyDefaults(true);
        try {
            langYaml.save(langFile);
        } catch (IOException | NullPointerException ex) {
            ChatUtil.error(Message.ERROR_FILE_IO.getMessage().replace("{file}", "messages_" + locale + ".yml"));
            ex.printStackTrace();
            return;
        }
    }

}
