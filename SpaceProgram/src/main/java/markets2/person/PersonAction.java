package markets2.person;

import org.jetbrains.annotations.NotNull;

import common.NamedInterface;

//
public abstract class PersonAction implements NamedInterface {
    private final @NotNull Person person; //reference
    private final @NotNull String name;
    private final int initialDuration;
    private int remainingDuration;

    //
    public PersonAction(@NotNull Person person, @NotNull String name, int duration) {
        this.person = person;
        this.name = name;
        initialDuration = duration;
        remainingDuration = duration;
    }

    //call this to perform the action
    public final void perform() {
        if (remainingDuration > 0) {
            action();
            remainingDuration --;
        }
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }

    //override this for the action behavior
    public abstract void action();

    //
    public final @NotNull Person getPerson() {
        return person;
    }

    //
    public final int getInitialDuration() {
        return initialDuration;
    }

    //
    public final int getRemainingDuration() {
        return remainingDuration;
    }
}