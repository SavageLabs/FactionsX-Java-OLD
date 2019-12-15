package io.illyria.factionsx.command;

import io.illyria.factionsx.command.argument.Argument;
import io.illyria.factionsx.config.Message;

import java.util.List;

public abstract class FCommand {

    public List<String> aliases;
    public CommandRequirements commandRequirements;
    public String prefix;
    public List<Argument> requiredArgs;
    public List<Argument> optionalArgs;
    public List<FCommand> subCommands;
    public boolean isBaseCommand;

    public FCommand() {}


    public abstract void perform(CommandContext context);

    public void execute(CommandContext context) {
        if (context.getArgs().size() > 0) {
            for (FCommand command : this.subCommands) {
                if (command.aliases.contains(context.getArgs().get(0).toLowerCase())) {
                    // Remove first so the arg for command processing is relative.
                    context.getArgs().remove(0);
                    // Recursive call
                    command.execute(context);
                    return;
                }
            }
        }

        if (!checkRequirements(context)) {
            return;
        }

        if (!isBaseCommand && checkInput(context)) {

        }
    }

    private boolean checkInput(CommandContext context) {
        if (context.getArgs().size() < requiredArgs.size()) {
            context.message(Message.COMMAND_REQUIREMENTS_TOOFEWARGS.getMessage());
            return false;
        } else if (context.getArgs().size() > requiredArgs.size() + optionalArgs.size()) {
            context.message(Message.COMMAND_REQUIREMENTS_TOOMANYARGS.getMessage());
            return false;
        }
        return true;
    }

    private void handleCommandFormat(CommandContext context) {
        if (context.isPlayer()) sendCommandFormat(context, true);
        else sendCommandFormat(context, false);
    }

    private void sendCommandFormat(CommandContext context, boolean json) {

    }

    private boolean checkRequirements(CommandContext context) {
        return commandRequirements.computeRequirements(context, true);
    }


}
