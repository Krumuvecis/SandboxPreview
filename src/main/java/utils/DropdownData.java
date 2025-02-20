package utils;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public class DropdownData<T extends @NotNull DropdownableInterface> {
    private final T defaultValue;
    private T value;

    //
    public DropdownData(T defaultValue) {
        this.defaultValue = defaultValue;
        value = defaultValue;
    }

    //
    public final T getDefaultValue() {
        return defaultValue;
    }

    //
    public final T getValue() {
        return value;
    }

    //null - default
    public final void setValue(@Nullable T value) {
        this.value = Objects.requireNonNullElse(value, defaultValue);
    }
}