package markets2.skills.particularSkills;

import org.jetbrains.annotations.NotNull;

import markets2.skills.SkillType;

//
public class VariableSkill extends AbstractSkill implements LearnableSkill, ForgettableSkill {
    //
    public VariableSkill(@NotNull SkillType type, double level) {
        super(type, level);
    }
}