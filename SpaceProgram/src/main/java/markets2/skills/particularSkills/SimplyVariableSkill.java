package markets2.skills.particularSkills;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import markets2.skills.SkillType;

//TODO: old variability model; rework
public final class SimplyVariableSkill extends VariableSkill {
    private static final @NotNull Random RANDOM = new Random();
    private static final double
            DEFAULT_FORGET_CHANCE = 0.05,
            DEFAULT_FORGET_RATE_MAX = 0.05,
            DEFAULT_FORGET_RATE_MIN = 0,
            DEFAULT_LEARN_CHANCE = 0.1,
            DEFAULT_LEARN_RATE_MAX = 0.1,
            DEFAULT_LEARN_RATE_MIN = 0;
    private final double
            forgetChance,
            forgetRateMax,
            forgetRateMin,
            learnChance,
            learnRateMax,
            learnRateMin;

    //fully-custom
    public SimplyVariableSkill(@NotNull SkillType type, double level,
                               double forgetChance, double forgetRateMax, double forgetRateMin,
                               double learnChance, double learnRateMax, double learnRateMin) {
        super(type, level);

        this.forgetChance = forgetChance;
        this.forgetRateMax = forgetRateMax;
        this.forgetRateMin = forgetRateMin;

        this.learnChance = learnChance;
        this.learnRateMax = learnRateMax;
        this.learnRateMin = learnRateMin;
    }

    //default forget/learn rates
    public SimplyVariableSkill(@NotNull SkillType type, double level) {
        this(type, level,
                DEFAULT_FORGET_CHANCE, DEFAULT_FORGET_RATE_MAX, DEFAULT_FORGET_RATE_MIN,
                DEFAULT_LEARN_CHANCE, DEFAULT_LEARN_RATE_MAX, DEFAULT_LEARN_RATE_MIN);
    }

    //
    public void learn() {
        learn(learnChance, getLearnRate());
    }

    private double getLearnRate() {
        return learnRateMin + RANDOM.nextDouble() * (learnRateMax - learnRateMin);
    }

    //
    public void forget() {
        forget(forgetChance, getForgetRate());
    }

    private double getForgetRate() {
        return forgetRateMin + RANDOM.nextDouble() * (forgetRateMax - forgetRateMin);
    }
}