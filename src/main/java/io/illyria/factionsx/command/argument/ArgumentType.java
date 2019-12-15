package io.illyria.factionsx.command.argument;

import io.illyria.factionsx.entity.IFPlayer;

import java.util.List;

public abstract class ArgumentType {
    abstract public List<String> getPossibleValues(IFPlayer fPlayer);
}
