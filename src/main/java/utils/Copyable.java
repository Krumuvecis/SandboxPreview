package utils;

import org.jetbrains.annotations.NotNull;

//TODO: add docs
@SuppressWarnings("MissingJavadoc")
public interface Copyable<T extends @NotNull Copyable<T>> {
    //
    T copy();
}