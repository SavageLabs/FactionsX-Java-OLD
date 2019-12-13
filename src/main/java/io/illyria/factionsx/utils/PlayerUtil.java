package io.illyria.factionsx.utils;

import org.bukkit.entity.Player;

public class PlayerUtil {

	// ----------------------------------------------------------------------
	// Utility methods that are related to the player.
	// ----------------------------------------------------------------------
	/**
	 * Description: Checks to see if the player has a set permission.
	 *
	 * @param player Player in choice.
	 * @param permission Permission we are testing for.
	 * @return
	 */
	public static boolean hasPerm(Player player, String permission){
		return player.hasPermission(permission);
	}

}
