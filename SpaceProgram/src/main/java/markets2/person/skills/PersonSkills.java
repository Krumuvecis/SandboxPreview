package markets2.person.skills;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.UpdatableInterface;

//
public final class PersonSkills extends PersonSkillContainer implements UpdatableInterface {
    private static final @NotNull Random RANDOM = new Random();
    private static final double
            SKILL_INHERITANCE_MAX_RATIO = 1,
            DEFAULT_MINIMUM_SKILL = 0.6,
            DEFAULT_MAXIMUM_SKILL = 1;

    //fully-custom values
    private PersonSkills(@NotNull Map<@NotNull PersonSkillType, @NotNull PersonSkill> skills) {
        super(skills);
    }

    //parent-inherited values; null generates default skills
    public PersonSkills(@Nullable PersonSkills parentSkills) {
        this(getInitialSkills(parentSkills));
    }

    private static @NotNull Map<@NotNull PersonSkillType, @NotNull PersonSkill> getInitialSkills(@Nullable PersonSkills parentSkills) {
        if (parentSkills == null) {
            return getNewSkills_default();
        } else {
            return getNewSkills_inherited(parentSkills);
        }
    }

    private static double getNewSkillBase() {
        return DEFAULT_MINIMUM_SKILL + RANDOM.nextDouble() * (DEFAULT_MAXIMUM_SKILL - DEFAULT_MINIMUM_SKILL);
    }

    private static @NotNull Map<@NotNull PersonSkillType, @NotNull PersonSkill> getNewSkills_default() {
        return new HashMap<>() {{
            put(PersonSkillType.GATHERING, new PersonSkill(PersonSkillType.GATHERING, getNewSkillBase()));
            put(PersonSkillType.PROCESSING, new PersonSkill(PersonSkillType.PROCESSING, getNewSkillBase()));
            put(PersonSkillType.CRAFTING, new PersonSkill(PersonSkillType.CRAFTING, getNewSkillBase()));
        }};
    }

    private static @NotNull Map<@NotNull PersonSkillType, @NotNull PersonSkill> getNewSkills_inherited(@NotNull PersonSkills parentSkills) {
        @NotNull @Unmodifiable Map<@NotNull PersonSkillType, @NotNull PersonSkill> parentSkillsMap = parentSkills.getAllSkills();
        return new HashMap<>() {{
            for (@NotNull PersonSkillType skillType : parentSkillsMap.keySet()) {
                double
                        skillBasePart = getNewSkillBase(),
                        parentSkillLevel = parentSkillsMap.get(skillType).getSkillLevel(),
                        skillInheritedPart = (parentSkillLevel - skillBasePart) * SKILL_INHERITANCE_MAX_RATIO * RANDOM.nextDouble(),
                        skillLevel = skillBasePart + skillInheritedPart;
                put(skillType, new PersonSkill(skillType, skillLevel));
            }
        }};
    }

    //
    @Override
    public void update() {
        forgetSkills(); //reduce skills
    }

    //learns by the default amount
    public void learnNewSkill(@NotNull PersonSkillType skillType) {
        learnNewSkill(skillType, new PersonSkill(skillType, getNewSkillBase()));
    }
}