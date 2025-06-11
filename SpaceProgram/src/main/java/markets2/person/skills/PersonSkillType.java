package markets2.person.skills;

import org.jetbrains.annotations.NotNull;

import common.NamedInterface;

//
public final class PersonSkillType implements NamedInterface {
    public static final @NotNull PersonSkillType
            GATHERING = new PersonSkillType("Gathering"),
            PROCESSING = new PersonSkillType("Processing"),
            CRAFTING = new PersonSkillType("Crafting");
    private final @NotNull String name;

    //
    private PersonSkillType(@NotNull String name) {
        this.name = name;
    }

    //
    @Override
    public @NotNull String getName() {
        return name;
    }
}