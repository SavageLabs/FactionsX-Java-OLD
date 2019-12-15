package io.illyria.factionsx.command.argument;

public class Argument {

    private String name;
    private int argumentIndex;
    private ArgumentType argumentType;

    public Argument(String name, int argumentIndex, ArgumentType argumentType) {
        this.name = name;
        this.argumentIndex = argumentIndex;
        this.argumentType = argumentType;
    }

    public String getName() {
        return name;
    }

    public int getArgumentIndex() {
        return argumentIndex;
    }

    public ArgumentType getArgumentType() {
        return argumentType;
    }
}
