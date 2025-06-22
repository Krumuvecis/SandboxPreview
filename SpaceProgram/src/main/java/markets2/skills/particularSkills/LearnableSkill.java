package markets2.skills.particularSkills;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import markets2.skills.SkillInterface;

//
interface LearnableSkill extends SkillInterface {
    @NotNull Random RANDOM = new Random();

    //chance supports all values; rate capped to [0, infinity]
    default void learn(double chance, double rate) {
        if (RANDOM.nextDouble() < chance) {
            multiply(1 + getCappedRate(rate));
        }
    }

    private double getCappedRate(double rate) {
        return Math.max(0, rate);
    }
}