package utils;

import org.jetbrains.annotations.NotNull;

//TODO: add docs
@SuppressWarnings("MissingJavadoc")
public interface DropdownableInterface {
    //for graphical purposes
    @Override
    @NotNull String toString();

    //
    <T extends @NotNull DropdownableInterface> T @NotNull [] getAll();
}