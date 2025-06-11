package markets2.person;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import common.NamedInterface;

//
class NamedPerson implements NamedInterface {
    private static final @NotNull String DEFAULT_NAME = "Person";
    private static int NEXT_UNNAMED_PERSON_INDEX = 1;
    private final @NotNull String name;

    //null name generates new name
    NamedPerson(@Nullable String name) {
        this.name = Objects.requireNonNullElse(name, getNewDefaultName());
    }

    private static @NotNull String getNewDefaultName() {
        @NotNull String name = DEFAULT_NAME + "-" + NEXT_UNNAMED_PERSON_INDEX;
        NEXT_UNNAMED_PERSON_INDEX ++;
        return name;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }
}