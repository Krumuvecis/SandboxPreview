package markets2.skills.particularSkills;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import markets2.skills.SkillInterface;

//
interface ForgettableSkill extends SkillInterface {
    @NotNull Random RANDOM = new Random();

    //chance supports all values; rate capped to [0, 1]
    default void forget(double chance, double rate) {
        if (RANDOM.nextDouble() < chance) {
            multiply(1 - getCappedRate(rate));
        }
    }

    private double getCappedRate(double rate) {
        return Math.max(0, Math.min(1, rate));
    }
}