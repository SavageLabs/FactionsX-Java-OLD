package io.illyria.factionsx.command;

import io.illyria.factionsx.command.cmd.CmdCreate;
import io.illyria.factionsx.command.cmd.CmdInvite;
import io.illyria.factionsx.command.cmd.CmdJoin;
import io.illyria.factionsx.command.engine.CommandContext;
import io.illyria.factionsx.command.engine.CommandRequirements;
import io.illyria.factionsx.command.engine.FCommand;
import io.illyria.factionsx.config.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.LinkedList;

public class FBaseCommand extends FCommand implements CommandExecutor {


    public FBaseCommand() {
        this.prefix = "f";
        this.commandRequirements = new CommandRequirements.Builder().build();
        super.subCommands.add(new CmdCreate());
        super.subCommands.add(new CmdInvite());
        super.subCommands.add(new CmdJoin());

    }


    @Override
    public void perform(CommandContext context) {
        if (!context.isPlayer()) {
            context.message(Message.CMD_BASE_HELP.getMessage());
            // TODO: Generate Help
            return;
        }
        if (context.getArgs().isEmpty()) {
            // TODO: Create some sort of menu to show.
        } else {
            context.message(Message.CMD_BASE_HELP.getMessage());
            // TODO: Generate Help.
        }

    }

    @Override
    public String getHelpInfo() {
        return Message.CMD_BASE_HELP.getMessage();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.execute(new CommandContext(sender, new LinkedList<>(Arrays.asList(args)), label));
        return true;
    }


}
