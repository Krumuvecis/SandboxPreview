package markets2.person.skills;

import java.util.Random;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

//
class PersonSkillContainer {
    private final @NotNull Map<@NotNull PersonSkillType, @NotNull PersonSkill> skills;

    //
    PersonSkillContainer(@NotNull Map<@NotNull PersonSkillType, @NotNull PersonSkill> skills) {
        this.skills = skills;
    }

    //gets all skills
    public final @NotNull @Unmodifiable Map<@NotNull PersonSkillType, @NotNull PersonSkill> getAllSkills() {
        return Map.copyOf(skills);
    }

    //
    public final @NotNull @Unmodifiable List<@NotNull PersonSkill> getSortedSkills() {
        @NotNull Set<@NotNull PersonSkill> unsortedSkills = new HashSet<>(skills.values());
        @NotNull List<@NotNull PersonSkill> sortedSkills = new ArrayList<>();
        while (!unsortedSkills.isEmpty()) {
            @Nullable PersonSkill highestSkill = null;
            double highestSkillLevel = 0;

            for (@NotNull PersonSkill skill : unsortedSkills) {
                double skillLevel = skill.getSkillLevel();
                if (skillLevel > highestSkillLevel) {
                    highestSkill = skill;
                    highestSkillLevel = skillLevel;
                }
            }

            if (highestSkill == null) {
                break;
            } else {
                unsortedSkills.remove(highestSkill);
                sortedSkills.add(highestSkill);
            }
        }
        return List.copyOf(sortedSkills);
    }

    //gets a single skill; null means no such skill
    public final @Nullable PersonSkill getSkill(@NotNull PersonSkillType skillType) {
        return skills.get(skillType);
    }

    //
    final void learnNewSkill(@NotNull PersonSkillType skillType, @NotNull PersonSkill skill) {
        skills.put(skillType, skill);
    }

    //increases an existing skill
    public final void learnSkill(@NotNull PersonSkillType skillType) {
        if (skills.containsKey(skillType)) {
            skills.get(skillType).learnSkill();
        }
    }

    //
    final void forgetSkills() {
        skills.keySet().forEach(skillType -> skills.get(skillType).forgetSkill()); //reduce skills
    }
}