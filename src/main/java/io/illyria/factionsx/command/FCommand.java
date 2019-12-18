package io.illyria.factionsx.command;

import io.illyria.factionsx.command.argument.Argument;
import io.illyria.factionsx.config.Message;
import io.illyria.factionsx.utils.ChatUtil;
import me.rayzr522.jsonmessage.JSONMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FCommand {

    public List<String> aliases;
    public CommandRequirements commandRequirements;
    public String prefix;
    public List<Argument> requiredArgs;
    public List<Argument> optionalArgs;
    public List<FCommand> subCommands;
    public boolean isBaseCommand;

    public FCommand() {
    }


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

        if (!isBaseCommand && checkInput(context) && !checkInput(context)) {
            return;
        }
        perform(context);
    }

    private boolean checkInput(CommandContext context) {
        if (context.getArgs().size() < requiredArgs.size()) {
            context.message(Message.COMMAND_REQUIREMENTS_TOOFEWARGS.getMessage());
            handleCommandFormat(context);
            return false;
        } else if (context.getArgs().size() > requiredArgs.size() + optionalArgs.size()) {
            context.message(Message.COMMAND_REQUIREMENTS_TOOMANYARGS.getMessage());
            handleCommandFormat(context);
            return false;
        }
        return true;
    }

    private void handleCommandFormat(CommandContext context) {
        if (context.isPlayer()) sendCommandFormat(context, true);
        else sendCommandFormat(context, false);
    }

    private void sendCommandFormat(CommandContext context, boolean json) {
        List<Argument> arguments = new ArrayList<>(requiredArgs);
        arguments.addAll(optionalArgs);
        requiredArgs = requiredArgs.stream().sorted(Comparator.comparing(Argument::getArgumentIndex)).collect(Collectors.toList());
        if (json) {
            JSONMessage commandFormatJSON = JSONMessage.create(ChatUtil.color("&7&o((Hoverable))&r")).then(" /" + prefix + " ")
                    .then(this.aliases.get(0));
            for (Argument argument : arguments) {
                commandFormatJSON = optionalArgs.contains(argument) ?
                        commandFormatJSON.then("(" + argument.getName() + ")").tooltip("The argument is optional.").then(" ") : commandFormatJSON.then("<" + argument.getName() + ">").tooltip("This argument is required.").then(" ");
            }
            commandFormatJSON.send(context.getPlayer());
        } else {
            StringBuilder commandFormat = new StringBuilder("/" + prefix + " ");
            for (Argument argument : requiredArgs) {
                commandFormat.append(optionalArgs.contains(argument) ? "(" + argument.getName() + ") " : "<" + argument.getName() + "> ");
            }
            context.message(commandFormat.toString());
        }
    }

    private boolean checkRequirements(CommandContext context) {
        return commandRequirements.computeRequirements(context, true);
    }


    


}
