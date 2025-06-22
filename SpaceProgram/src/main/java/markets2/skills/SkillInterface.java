package markets2.skills;

import org.jetbrains.annotations.NotNull;

//
public interface SkillInterface {
    //
    @NotNull SkillType getType();

    //
    double getLevel();

    //
    void multiply(double multiplier);
}