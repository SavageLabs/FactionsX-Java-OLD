package io.illyria.factionsx.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

public class RegisterUtil {

	/**
	 * Description: Lazy persons way of registering commands also by passes the plugin.yml file.
	 *
	 * @param command The set command you want to register.
	 */

	public static void registerCommand(Command command) {
		try {
			final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			commandMapField.setAccessible(true);

			final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
			commandMap.register(command.getLabel(), command);

		} catch(final Exception e) {
			e.printStackTrace();
		}
	}
}
