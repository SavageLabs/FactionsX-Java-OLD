package io.illyria.factionsx.command.cmd;

import io.illyria.factionsx.command.engine.CommandContext;
import io.illyria.factionsx.command.engine.CommandRequirements;
import io.illyria.factionsx.command.engine.FCommand;
import io.illyria.factionsx.core.Permission;

public class CmdTest extends FCommand {

    public CmdTest() {
        super();
        aliases.add("test");

        commandRequirements = new CommandRequirements.Builder().withPermission(Permission.EXAMPLE).asPlayer(true).build();
    }


    @Override
    public void perform(CommandContext context) {
        context.message("Passed.");
    }

    @Override
    public String getHelpInfo() {
        return null;
    }
}
