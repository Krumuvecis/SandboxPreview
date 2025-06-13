package markets2.resources;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//
public final class UnrecognizedResourceType extends Exception {
    //
    public UnrecognizedResourceType(@Nullable String customMessage) {
        super(getMessage(customMessage));
    }

    //
    public UnrecognizedResourceType() {
        this(null);
    }

    private static @NotNull String getMessage(@Nullable String customMessage) {
        @NotNull String message = "Unrecognized resource type.";
        if (customMessage != null) {
            message += " " + customMessage;
        }
        return message;
    }
}