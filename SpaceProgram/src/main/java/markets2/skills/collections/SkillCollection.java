package markets2.skills.collections;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.skills.SkillType;
import markets2.skills.SkillInterface;
import markets2.skills.particularSkills.VariableSkill;
import markets2.skills.NonVariableSkillException;
import markets2.skills.SkillNotFoundException;

//
public interface SkillCollection {
    //
    @NotNull @Unmodifiable Map<@NotNull SkillType, @NotNull SkillInterface> getMap();

    //
    default @NotNull @Unmodifiable Set<@NotNull SkillInterface> getAll() {
        return Set.copyOf(getMap().values());
    }

    //
    default @NotNull @Unmodifiable List<@NotNull SkillInterface> getSorted() {
        @NotNull Set<@NotNull SkillInterface> unsorted = new HashSet<>(getAll());
        @NotNull List<@NotNull SkillInterface> sorted = new ArrayList<>();
        while (!unsorted.isEmpty()) {
            @Nullable SkillInterface highestSkill = null;
            double highestLevel = 0;
            for (@NotNull SkillInterface skill : unsorted) {
                double level = skill.getLevel();
                if (level > highestLevel) {
                    highestSkill = skill;
                    highestLevel = level;
                }
            }
            if (highestSkill == null) {
                break;
            } else {
                unsorted.remove(highestSkill);
                sorted.add(highestSkill);
            }
        }
        return List.copyOf(sorted);
    }

    //gets a single skill; null means no such skill
    default @Nullable SkillInterface get(@NotNull SkillType type) {
        return getMap().get(type);
    }

    //adds a new skill
    void put(@NotNull SkillInterface skill);

    //increases an existing skill
    default void learn(@NotNull SkillType type,
                       double chance, double rate) throws NonVariableSkillException, SkillNotFoundException {
        @Nullable SkillInterface skill = get(type);
        if (skill == null) {
            throw new SkillNotFoundException();
        } else {
            learnIfVariable(skill, chance, rate);
        }
    }

    private void learnIfVariable(@NotNull SkillInterface skill,
                                 double chance, double rate) throws NonVariableSkillException {
        if (skill instanceof @NotNull VariableSkill variableSkill) {
            variableSkill.learn(chance, rate);
        } else {
            throw new NonVariableSkillException();
        }
    }

    //decreases an existing skill
    default void forget(@NotNull SkillType type,
                        double chance, double rate) throws NonVariableSkillException, SkillNotFoundException {
        @Nullable SkillInterface skill = get(type);
        if (skill == null) {
            throw new SkillNotFoundException();
        } else {
            forgetIfVariable(skill, chance, rate);
        }
    }

    //decreases all existing skills
    default void forgetAll(double chance, double rate) {
        for (@NotNull SkillInterface skill : getAll()) {
            try {
                forgetIfVariable(skill, chance, rate);
            } catch (@NotNull NonVariableSkillException ignored) {}
        }
    }

    private void forgetIfVariable(@NotNull SkillInterface skill,
                                  double chance, double rate) throws NonVariableSkillException {
        if (skill instanceof @NotNull VariableSkill variableSkill) {
            variableSkill.forget(chance, rate);
        } else {
            throw new NonVariableSkillException();
        }
    }
}