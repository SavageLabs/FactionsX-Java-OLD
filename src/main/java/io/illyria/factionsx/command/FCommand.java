package io.illyria.factionsx.command;

import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.utils.ChatUtil;
import io.illyria.factionsx.utils.PlayerUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class FCommand extends Command {

	/*
	 *TODO:
	 * - Setup command stealing in case other plugins are using the command we are trying to use the commands we are trying to use.
	 * - Dynamic registering right through the constructor of the command?
	 * - Setup subcommands and subcommand groups.
	 * 	 	- Example : "/faction" (set in it's own class.) then another class that handles "/faction help" the help section is in it's own class while the main command
	 *      - Group handles the the execution and registering of the sub command.
	 * - Implement more factions based "checks"
	 * - Setup dynamic permissions? example command.factionx.{label}
	 */

	protected FCommand(final String name) {
		super(name);
	}

	// ----------------------------------------------------------------------
	// Variables resets every time a command is being executed.
	// ----------------------------------------------------------------------

	// Player or Console who executed the command.
	protected CommandSender sender;

	// Arguments that are passed to the command.
	protected String[] args;

	// Command name that is being executed not really needed but might be handy.
	private String label;

	// ----------------------------------------------------------------------
	// Execute manages the command execution.
	// ----------------------------------------------------------------------

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		this.sender = sender;
		this.label = label;
		this.args = args;

		try {
			executeCommand();
		} catch (ReturnedCommandException ex) {
			final String tellMessage = ex.tellMessage;

			tell(tellMessage);
		}
		return false;
	}

	// Method that is used instead of "execute"
	protected abstract void executeCommand();

	// ----------------------------------------------------------------------
	// Checks simple check methods to reduce the amount of code that needs to be written.
	// ----------------------------------------------------------------------

	/**
	 * Description: Checks to see if the player is in a faction before executing.
	 */
	protected final void checkFaction() {
		// TODO
	}

	/**
	 * Description: Checks the minimum to see the amount of arguments and if the arguments provided from the command sender is less than
	 * the minimum arguments it return false.
	 *
	 * @param minimumArguments Minimum amount of arguments that you can have.
	 * @param errorMessage     Returns a message if the amount of arguments is not hit.
	 */
	protected final void checkArguments(int minimumArguments, String errorMessage) {
		if (args.length < minimumArguments)
			returnTell(errorMessage);
	}

	/**
	 * Description: Checks to see if the person executing is the console if so
	 * it returns an error message.
	 */
	protected final void checkConsole() {
		if (!isPlayer())
			returnTell((Message.ERROR_CONSOLE_COMMAND_EXECUTION.getMessages().toString()));
	}

	/**
	 * Description: Checks to see if the player has the specific permission
	 *
	 * @param permissionToCheck The permission we are checking for.
	 * @param errorMessage      Returns a message if the player dose not have a permission.
	 */
	public final void checkPermission(String permissionToCheck, String errorMessage) {
		if (isPlayer() && !PlayerUtil.hasPerm((Player) sender, permissionToCheck))
			returnTell(errorMessage);
	}

	/**
	 * Description: Check to see if an argument at an index is an int.
	 *
	 * @param argumentPosition The position that we look for the number.
	 * @param notNumberMessage Error message when the player dose not enter a valid number.
	 * @return The number we are parsing to make sure it is an number.
	 */
	public int getInt(int argumentPosition, String notNumberMessage) {
		int returnNumber = 0;

		try {
			if (argumentPosition > args.length)
				ChatUtil.error("[Command: /" + label + "] Trying to parse an int out side of the amount of arguments length.");

			returnNumber = Integer.parseInt(args[argumentPosition]);
		} catch (NumberFormatException ex) {
			tell(notNumberMessage);
		}
		// Number that's returned after being parsed.
		return returnNumber;
	}

	/**
	 * Description: Check to see if the boolean value is true or false.
	 *
	 * @param toCheck             The boolean value we are trying to check
	 * @param falseMessageMessage Message that will be returned if the boolean value is false
	 */
	protected void checkBoolean(boolean toCheck, String falseMessage) {
		if (!toCheck)
			returnTell(falseMessage);
	}

	/**
	 * Description: Checks to see if the object is not null.
	 *
	 * @param toCheck     Object that is being checked.
	 * @param nullMessage Message that is being returned if the object is null.
	 */
	protected void checkNotNull(Object toCheck, String nullMessage) {
		if (toCheck == null)
			returnTell(nullMessage);
	}

	// ----------------------------------------------------------------------
	// Utility methods from the underlying command class from Command class.
	// ----------------------------------------------------------------------

	/**
	 * Description: Checks to see if the send is a player if it's a player it returns a player
	 * if its not a player it sends null.
	 */
	protected final Player getPlayer() {
		return isPlayer() ? (Player) sender : null;
	}

	/**
	 * Description: Check to see if the sender is a player.
	 */
	protected boolean isPlayer() {
		return sender instanceof Player;
	}

	/**
	 * Description: Sends a message then throws and exception to stop further processing of the command.
	 *
	 * @param message
	 */
	protected void returnTell(String message) {
		throw new ReturnedCommandException(message);
	}

	/**
	 * Description: Overlaying to reduce the amount of repetitive code supports single lined and multi lined messages.
	 * Throws an exception to prevent further execution of the code.
	 *
	 * @param messages Sends the player a single lined or multi lined message.
	 */
	protected void tell(String... messages) {
		ChatUtil.message(sender, messages);
	}

	// ----------------------------------------------------------------------
	// Exception
	// ----------------------------------------------------------------------

	/**
	 * Description: Custom exception that cancel the rest of the command from executing is the exception is hit.
	 */
	@RequiredArgsConstructor
	final class ReturnedCommandException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		private final String tellMessage;
	}
}
