package markets2.processes;

import org.jetbrains.annotations.NotNull;

//
public abstract class AbstractProcess implements ProcessInterface {
    private final @NotNull String name;
    private final double duration;

    //
    public AbstractProcess(@NotNull String name, double duration) {
        this.name = name;
        this.duration = duration;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }

    //
    @Override
    public final double getDuration() {
        return duration;
    }
}