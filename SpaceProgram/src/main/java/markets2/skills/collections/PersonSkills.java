package markets2.skills.collections;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.skills.SkillType;
import markets2.skills.particularSkills.SimplyVariableSkill;
import markets2.UpdatableInterface;

//TODO: old
public final class PersonSkills extends SkillContainer implements UpdatableInterface {
    private static final @NotNull Random RANDOM = new Random();
    private static final double
            SKILL_INHERITANCE_MAX_RATIO = 1,
            DEFAULT_MINIMUM_SKILL = 0.6,
            DEFAULT_MAXIMUM_SKILL = 1;

    //fully-custom values
    private PersonSkills(@NotNull Map<@NotNull SkillType, @NotNull SimplyVariableSkill> skills) {
        super(skills);
    }

    //parent-inherited values; null generates default skills
    public PersonSkills(@Nullable PersonSkills parentSkills) {
        this(getInitialSkills(parentSkills));
    }

    private static @NotNull Map<@NotNull SkillType, @NotNull SimplyVariableSkill> getInitialSkills(@Nullable PersonSkills parentSkills) {
        if (parentSkills == null) {
            return getNewSkills_default();
        } else {
            return getNewSkills_inherited(parentSkills);
        }
    }

    private static double getNewSkillBase() {
        return DEFAULT_MINIMUM_SKILL + RANDOM.nextDouble() * (DEFAULT_MAXIMUM_SKILL - DEFAULT_MINIMUM_SKILL);
    }

    private static @NotNull Map<@NotNull SkillType, @NotNull SimplyVariableSkill> getNewSkills_default() {
        return new HashMap<>() {{
            put(SkillType.GATHERING, new SimplyVariableSkill(SkillType.GATHERING, getNewSkillBase()));
            put(SkillType.PROCESSING, new SimplyVariableSkill(SkillType.PROCESSING, getNewSkillBase()));
            put(SkillType.CRAFTING, new SimplyVariableSkill(SkillType.CRAFTING, getNewSkillBase()));
        }};
    }

    private static @NotNull Map<@NotNull SkillType, @NotNull SimplyVariableSkill> getNewSkills_inherited(@NotNull PersonSkills parentSkills) {
        @NotNull @Unmodifiable Map<@NotNull SkillType, @NotNull SimplyVariableSkill> parentSkillsMap = parentSkills.getAllSkills();
        return new HashMap<>() {{
            for (@NotNull SkillType skillType : parentSkillsMap.keySet()) {
                double
                        skillBasePart = getNewSkillBase(),
                        parentSkillLevel = parentSkillsMap.get(skillType).getSkillLevel(),
                        skillInheritedPart = (parentSkillLevel - skillBasePart) * SKILL_INHERITANCE_MAX_RATIO * RANDOM.nextDouble(),
                        skillLevel = skillBasePart + skillInheritedPart;
                put(skillType, new SimplyVariableSkill(skillType, skillLevel));
            }
        }};
    }

    //
    @Override
    public void update() {
        forgetAll(); //reduce skills
    }

    //learns by the default amount
    public void learnNewSkill(@NotNull SkillType skillType) {
        put(new SimplyVariableSkill(skillType, getNewSkillBase()));
    }
}