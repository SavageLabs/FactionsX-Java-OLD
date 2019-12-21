package io.illyria.factionsx.command.engine.argument;

import io.illyria.factionsx.entity.IFPlayer;

import java.util.Collections;
import java.util.List;

public class StringArgument extends ArgumentType {
    @Override
    public List<String> getPossibleValues(IFPlayer fPlayer) {
        return Collections.emptyList();
    }
}
