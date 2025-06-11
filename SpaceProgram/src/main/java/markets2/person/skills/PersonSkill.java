package markets2.person.skills;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

//
public final class PersonSkill {
    private static final @NotNull Random RANDOM = new Random();
    private static final double
            DEFAULT_SKILL_FORGET_CHANCE = 0.05,
            DEFAULT_SKILL_FORGET_RATE_MAX = 0.05,
            DEFAULT_SKILL_FORGET_RATE_MIN = 0,
            DEFAULT_SKILL_LEARN_CHANCE = 0.1,
            DEFAULT_SKILL_LEARN_RATE_MAX = 0.1,
            DEFAULT_SKILL_LEARN_RATE_MIN = 0;
    private final @NotNull PersonSkillType skillType; //reference
    private final double
            skillForgetChance,
            skillForgetRateMax,
            skillForgetRateMin,
            skillLearnChance,
            skillLearnRateMax,
            skillLearnRateMin;
    private double skillLevel;

    //fully-custom
    public PersonSkill(@NotNull PersonSkillType skillType, double skillLevel,
                       double skillForgetChance, double skillForgetRateMax, double skillForgetRateMin,
                       double skillLearnChance, double skillLearnRateMax, double skillLearnRateMin) {
        this.skillType = skillType;

        this.skillForgetChance = skillForgetChance;
        this.skillForgetRateMax = skillForgetRateMax;
        this.skillForgetRateMin = skillForgetRateMin;
        this.skillLearnChance = skillLearnChance;
        this.skillLearnRateMax = skillLearnRateMax;
        this.skillLearnRateMin = skillLearnRateMin;

        this.skillLevel = skillLevel;
    }

    //default forget/learn rates
    public PersonSkill(@NotNull PersonSkillType skillType, double skillLevel) {
        this(skillType, skillLevel,
                DEFAULT_SKILL_FORGET_CHANCE, DEFAULT_SKILL_FORGET_RATE_MAX, DEFAULT_SKILL_FORGET_RATE_MIN,
                DEFAULT_SKILL_LEARN_CHANCE, DEFAULT_SKILL_LEARN_RATE_MAX, DEFAULT_SKILL_LEARN_RATE_MIN);
    }

    //
    public @NotNull PersonSkillType getSkillType() {
        return skillType;
    }

    //
    public double getSkillLevel() {
        return skillLevel;
    }

    //
    public void forgetSkill() {
        if (RANDOM.nextDouble() < skillForgetChance) {
            double forgetRate = skillForgetRateMin + RANDOM.nextDouble() * (skillForgetRateMax - skillForgetRateMin);
            skillLevel *= (1 - forgetRate);
        }
    }

    //
    public void learnSkill() {
        if (RANDOM.nextDouble() < skillLearnChance) {
            double learnRate = skillLearnRateMin + RANDOM.nextDouble() * (skillLearnRateMax - skillLearnRateMin);
            skillLevel *= (1 + learnRate);
        }
    }
}