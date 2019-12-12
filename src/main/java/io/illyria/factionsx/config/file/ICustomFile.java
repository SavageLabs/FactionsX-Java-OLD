package io.illyria.factionsx.config.file;

public interface ICustomFile<T> {

    T init();

    String getName();

}
