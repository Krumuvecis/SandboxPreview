package markets2.skills;

import org.jetbrains.annotations.NotNull;

import common.NamedInterface;

//
public final class SkillType implements NamedInterface {
    public static final @NotNull SkillType
            GATHERING = new SkillType("Gathering"),
            PROCESSING = new SkillType("Processing"),
            CRAFTING = new SkillType("Crafting");
    private final @NotNull String name;

    //
    private SkillType(@NotNull String name) {
        this.name = name;
    }

    //
    @Override
    public @NotNull String getName() {
        return name;
    }
}