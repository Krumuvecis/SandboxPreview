package markets2.person.actions;

import org.jetbrains.annotations.NotNull;

import common.NamedInterface;
import markets2.person.Person;

//
public abstract class PersonAction<T extends @NotNull PersonActionTemplate> implements NamedInterface {
    private final @NotNull T actionTemplate; //reference
    private final @NotNull Person person; //reference
    private final int initialDuration;
    private int remainingDuration;

    //
    public PersonAction(T actionTemplate, @NotNull Person person, int duration) {
        this.actionTemplate = actionTemplate;
        this.person = person;
        initialDuration = duration;
        remainingDuration = duration;
    }

    //for internal use
    final T getActionTemplate() {
        return actionTemplate;
    }

    //reference
    public final @NotNull Person getPerson() {
        return person;
    }

    //
    @Override
    public final @NotNull String getName() {
        return actionTemplate.getName();
    }

    //call this to perform the action
    public final void perform() {
        if (remainingDuration > 0) {
            action();
            remainingDuration --;
        }
    }

    //override this for the action behavior
    public abstract void action();

    //
    public final int getInitialDuration() {
        return initialDuration;
    }

    //
    public final int getRemainingDuration() {
        return remainingDuration;
    }
}