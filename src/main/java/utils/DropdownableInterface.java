package utils;

import org.jetbrains.annotations.NotNull;

//TODO: add docs
@SuppressWarnings("MissingJavadoc")
public interface DropdownableInterface<T extends @NotNull DropdownableInterface<T>> {
    //for graphical purposes
    @Override
    @NotNull String toString();

    //
    T @NotNull [] getAll();
}