package io.illyria.factionsx.command.argument;

import io.illyria.factionsx.entity.IFPlayer;

import java.util.Arrays;
import java.util.List;

public class BooleanArgument extends ArgumentType {
    @Override
    public List<String> getPossibleValues(IFPlayer fPlayer) {
        return Arrays.asList("true", "false");
    }
}
