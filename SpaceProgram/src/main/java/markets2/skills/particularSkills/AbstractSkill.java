package markets2.skills.particularSkills;

import org.jetbrains.annotations.NotNull;

import markets2.skills.SkillType;
import markets2.skills.SkillInterface;

//
abstract class AbstractSkill implements SkillInterface {
    private final @NotNull SkillType type;
    private double level;

    //
    AbstractSkill(@NotNull SkillType type, double level) {
        this.type = type;
        this.level = level;
    }

    //
    @Override
    public final @NotNull SkillType getType() {
        return type;
    }

    //
    @Override
    public final double getLevel() {
        return level;
    }

    //
    @Override
    public final void multiply(double multiplier) {
        level *= multiplier;
    }
}