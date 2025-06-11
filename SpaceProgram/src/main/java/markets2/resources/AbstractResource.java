package markets2.resources;

import org.jetbrains.annotations.NotNull;

//
abstract class AbstractResource implements ResourceInterface {
    private final @NotNull String name;

    //
    AbstractResource(@NotNull String name) {
        this.name = name;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }
}