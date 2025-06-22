package markets2.skills.collections;

import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import markets2.skills.SkillType;
import markets2.skills.SkillInterface;

//
public class SkillContainer implements SkillCollection {
    private final @NotNull Map<@NotNull SkillType, @NotNull SkillInterface> skills = new HashMap<>();

    //
    public SkillContainer() {}

    //
    @Override
    public final @NotNull @Unmodifiable Map<@NotNull SkillType, @NotNull SkillInterface> getMap() {
        return Map.copyOf(skills);
    }

    //adds a new skill; doesn't overwrite
    @Override
    public final void put(@NotNull SkillInterface skill) {
        @NotNull SkillType type = skill.getType();
        skills.putIfAbsent(type, skill);
    }
}